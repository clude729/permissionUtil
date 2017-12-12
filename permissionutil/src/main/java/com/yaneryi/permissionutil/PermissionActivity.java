package com.yaneryi.permissionutil;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;

import com.yaneryi.permissionutil.dialog.OnDialogDismissListener;
import com.yaneryi.permissionutil.dialog.OnDialogClickListener;
import com.yaneryi.permissionutil.safe.SafeIntent;
import com.yaneryi.permissionutil.utils.ArrayUtils;
import com.yaneryi.permissionutil.utils.EnvironmentEx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用于申请权限的Activity
 * Created by clude on 2017/11/29.
 */
public class PermissionActivity extends Activity {

    /**
     * Key to retrieve requested permissions from the intent.
     */
    private static final String EXTRA_PERMISSION_REQUESTED_PERMISSIONS = "requested_permissions";

    /**
     * Key to retrieve request code from the intent.
     */
    private static final String EXTRA_PERMISSION_REQUEST_CODE = "request_code";

    /**
     * Key to retrieve explain message from the intent.
     */
    private static final String EXTRA_PERMISSION_EXPLAIN_MSG = "explain_message";

    private static final Set<String> NOT_IMPORTANT_PERMISSIONS = new HashSet<String>(2);

    static {
        NOT_IMPORTANT_PERMISSIONS.add(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private static final boolean ENABLE_GUIDE = true;

    private static final int INVALID_REQUEST_CODE = -1;
    private static final String TAG = "PermissionActivity";

    private int mPendingRequestCode = INVALID_REQUEST_CODE;
    private long requestTime;
    private String[] permissions;

    /**
     * Starts a PermissionsActivity and checks/requests supplied permissions.
     *
     * @param requestCode       requestCode
     * @param explainMsg        explain Msg
     * @param permissionStrings permissions to request
     */
    public static void run(int requestCode, int explainMsg, String... permissionStrings) {
        Context c = EnvironmentEx.getApplicationContext();
        run(c, requestCode, explainMsg, permissionStrings);
    }

    /**
     * Starts a PermissionsActivity and checks/requests supplied permissions.
     *
     * @param context           context
     * @param requestCode       requestCode
     * @param explainMsg        explain Msg
     * @param permissionStrings permissions to request
     */
    public static void run(Context context, int requestCode, int explainMsg, String... permissionStrings) {
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(EXTRA_PERMISSION_REQUESTED_PERMISSIONS, permissionStrings);
        intent.putExtra(EXTRA_PERMISSION_REQUEST_CODE, requestCode);
        intent.putExtra(EXTRA_PERMISSION_EXPLAIN_MSG, explainMsg);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        //防止不弹框时界面黑一下的情况
        getWindow().setDimAmount(0);
        mPendingRequestCode = (savedInstanceState != null)
                ? savedInstanceState.getInt(EXTRA_PERMISSION_REQUEST_CODE, INVALID_REQUEST_CODE) : INVALID_REQUEST_CODE;
        Log.i(TAG, "onCreate mPendingRequestCode :" + mPendingRequestCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onNewIntent(Intent intent) {
        mPendingRequestCode = INVALID_REQUEST_CODE;
        setIntent(intent);
        Log.i(TAG, "onNewIntent");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_PERMISSION_REQUEST_CODE, mPendingRequestCode);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();
        // Only do request when there is no pending request to avoid duplicated requests.
        Intent intent = getIntent();
        if (mPendingRequestCode == INVALID_REQUEST_CODE && null != intent) {
            intent = new SafeIntent(intent);
            final Bundle extras = intent.getExtras();
            if (null != extras) {
                permissions = extras.getStringArray(EXTRA_PERMISSION_REQUESTED_PERMISSIONS);
                final int msg = extras.getInt(EXTRA_PERMISSION_EXPLAIN_MSG, 0);
                mPendingRequestCode = extras.getInt(EXTRA_PERMISSION_REQUEST_CODE);
                requestTime = SystemClock.elapsedRealtime();
                PermissionUtils.requestPermissionIfNeed(this, permissions, mPendingRequestCode, msg);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        if (mPendingRequestCode != INVALID_REQUEST_CODE) {
            //this activity may be destroyed background
            PermissionUtils.onRequestPermissionsResult(mPendingRequestCode,
                    ArrayUtils.isEmpty(PermissionUtils.checkPermission(permissions)));
        }
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean granted = PermissionUtils.verifyPermissions(grantResults);
        if (ENABLE_GUIDE && !granted && isAutoRefused()) {
            //try to let user open permission from Settings
            Log.i(TAG, "User do not want this permission, and the system dialog did not show.");
            String[] needRequirePermissions = pick(permissions, grantResults);
            if (containImportantPermissions(needRequirePermissions)) {
                //show dialog
                showGuideDialog(requestCode, needRequirePermissions);
                return;
            }
        }

        mPendingRequestCode = INVALID_REQUEST_CODE;
        PermissionUtils.onRequestPermissionsResult(requestCode, granted);
        finish();
    }

    private boolean containImportantPermissions(String[] permissions) {
        if (ArrayUtils.isEmpty(permissions)) {
            return false;
        }

        for (String permisson : permissions) {
            if (!NOT_IMPORTANT_PERMISSIONS.contains(permisson)) {
                return true;
            }
        }

        return false;
    }

    private void showGuideDialog(final int requestCode, String[] needRequirePermissions) {
        final PermissionGuideDialog dialog = PermissionGuideDialog.newInstance(needRequirePermissions);
        dialog.setOnDialogClickListener(new OnDialogClickListener() {
            @Override
            public void onPositive() {
                dialog.setOnDismissListener(null);
                gotoPermissionSettings(requestCode);
            }

            public void onNeutral(){

            }

            public void onNegative(){

            }

        });
        dialog.setOnDismissListener(new OnDialogDismissListener() {
            @Override
            public void onDismiss() {
                mPendingRequestCode = INVALID_REQUEST_CODE;
                PermissionUtils.onRequestPermissionsResult(requestCode, false);
                finish();
            }
        });

        dialog.show(this);
    }

    private String[] pick(String[] permissions, int[] grantResults) {
        if (ArrayUtils.isEmpty(permissions)) {
            return permissions;
        }
        int len = permissions.length;
        List<String> result = new ArrayList<String>(len);
        for (int i = 0; i < len; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                result.add(permissions[i]);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private void gotoPermissionSettings(int requestCode) {
        String packageName = getPackageName();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException ex) {
            Log.e(TAG, "Settings.ACTION_APPLICATION_DETAILS_SETTINGS cannot open!");
            intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Why need check permissions are empty ?
        //If the activity restarted by refuse a permission in settings,
        //then when start a new instance , this callback will be called immediately.
        //In this case we do not wish to finish this activity, for we did not query permissions yet.
        if (requestCode == mPendingRequestCode && !ArrayUtils.isEmpty(permissions)) {
            Log.i(TAG, "onActivityResult");
            mPendingRequestCode = INVALID_REQUEST_CODE;
            PermissionUtils.onRequestPermissionsResult(requestCode,
                    ArrayUtils.isEmpty(PermissionUtils.checkPermission(permissions)));
            finish();
        }
    }

    /**
     * [自动拒绝就是在勾选了不再提示才会出现，表现就是刚请求就立即驳回]<BR>
     *
     * @return 是否是自动拒绝
     */
    private boolean isAutoRefused() {
        return SystemClock.elapsedRealtime() - requestTime < 500;
    }

}
