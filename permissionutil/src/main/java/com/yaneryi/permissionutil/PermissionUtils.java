package com.yaneryi.permissionutil;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.yaneryi.permissionutil.utils.ArrayUtils;
import com.yaneryi.permissionutil.utils.EnvironmentEx;

import java.util.ArrayList;

/**
 * M版本权限相关工具类
 * Created by clude on 2017/11/28.
 */
@TargetApi(23)
public final class PermissionUtils {

    /**
     * [异步请求权限]
     */
    public interface PermissonListener {
        /**
         * [回调结果]
         *
         * @param success 是否成功
         */
        void onRequested(boolean success);
    }

    private static final ContextThemeWrapper CONTEXT = (ContextThemeWrapper) EnvironmentEx.getApplicationThemeContext();

    private static final String[] EMPTY = new String[]{};

    private static final SparseArray<PermissonListener> LISTENERS = new SparseArray<PermissonListener>();

    private static final String TAG = "PermissionUtils";

    private static int mRequestId;

    private PermissionUtils() {

    }

    /**
     * [检查是否有权限]
     *
     * @param permission 权限
     * @return true:有权限
     */
    public static boolean checkPermission(String permission) {
        if (!isMNC()) {
            Log.d(TAG, "Build.VERSION.SDK_INT < 23");
            return true;
        }

        if (TextUtils.isEmpty(permission)) {
            Log.d(TAG, "permission is empty.");
            return false;
        }
//        Log.d(TAG, "CONTEXT.checkSelfPermission(permission) = " + CONTEXT.checkSelfPermission(permission));
        return CONTEXT.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * [检查是否有权限]
     *
     * @param permissons 原始的权限
     * @return 需要申请的权限
     */
    public static String[] checkPermission(String[] permissons) {
        if (!isMNC() || ArrayUtils.isEmpty(permissons)) {
            return EMPTY;
        }

        ArrayList<String> result = new ArrayList<String>();
        for (String permission : permissons) {
            if (!checkPermission(permission)) {
                result.add(permission);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * [如果没有权限，就申请权限]
     *
     * @param activity   activity
     * @param permissons 要检查的权限
     * @param code       请求的code
     * @param msg        请求前需要给出的提示语
     * @return 是否需要请求
     * @deprecated
     */
    public static boolean requestPermissionIfNeed(Activity activity, String[] permissons, int code, int msg) {
        if (activity == null) {
            return false;
        }

        String[] rest = checkPermission(permissons);
        boolean request = !ArrayUtils.isEmpty(rest);
        if (request) {
            explainIfNeed(activity, permissons, msg);
            activity.requestPermissions(rest, code);
        }
        return request;
    }

    private static void explainIfNeed(Activity activity, String[] permissons, int msg) {
        if (msg == 0) {
            return;
        }

        for (String permisson : permissons) {
            if (activity.shouldShowRequestPermissionRationale(permisson)) {
                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
//                ToastUtil.showLongToast(msg);
                return;
            }
        }
    }

    /**
     * Check that all given permissions have been granted by verifying that each entry in the given array is of the
     * value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @param grantResults results
     * @return true:granted
     */
    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults == null) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * [尝试申请账号权限]
     *
     * @param listener 回调
     * @return true:需要申请
     */
    public static boolean needRequestAccountPermission(PermissonListener listener) {
        if (!checkPermission(Manifest.permission.GET_ACCOUNTS)) {
            requestPermissionAsync(new String[]{Manifest.permission.GET_ACCOUNTS}, 0, listener);
            return true;
        }
        return false;
    }

    /**
     * [异步申请权限，适用于没有activity的情况，暂不支持跨进程的情况]
     *
     * @param permissons 要申请的权限
     * @param explainMsg 需要解释时，弹出的消息，不为0时有效
     * @param listener   回调监听
     */
    public static synchronized void requestPermissionAsync(String[] permissons, int explainMsg,
                                                           PermissonListener listener) {
        if (!isMNC() || ArrayUtils.isEmpty(permissons)) {
            callback(listener, true);
            return;
        }

        final int id = mRequestId++;
        LISTENERS.put(id, listener);
        Log.i(TAG, "requestPermissionAsync id:" + id + ", permissons:" + permissons[0]);
        PermissionActivity.run(id, explainMsg, permissons);
    }

    /**
     * [异步申请权限，适用于有activity的情况，暂不支持跨进程的情况]
     *
     * @param permissons 要申请的权限
     * @param explainMsg 需要解释时，弹出的消息，不为0时有效
     * @param listener   回调监听
     */
    public static synchronized void requestPermissionAsync(Activity activity, String[] permissons, int explainMsg,
                                                           PermissonListener listener) {
        if (!isMNC() || ArrayUtils.isEmpty(permissons)) {
            callback(listener, true);
            return;
        }

        final int id = mRequestId++;
        LISTENERS.put(id, listener);
        Log.i(TAG, "requestPermissionAsync id:" + id + ", permissons:" + permissons[0]);
        PermissionActivity.run(activity, id, explainMsg, permissons);
    }

    /**
     * [回调申请权限的结果]
     *
     * @param requestCode 请求编号
     * @param result      权限结果
     */
    public static synchronized void onRequestPermissionsResult(int requestCode, boolean result) {
        Log.i(TAG, "onRequestPermissionsResult requestCode:" + requestCode + ",result:" + result);
        PermissonListener listener = LISTENERS.get(requestCode);
        LISTENERS.remove(requestCode);
        callback(listener, result);
    }

    private static void callback(PermissonListener listener, boolean result) {
        if (listener != null) {
            listener.onRequested(result);
        }
    }

    /**
     * [获取权限的组名称]
     *
     * @param permission 权限名称
     * @return 权限组名称
     */
    public static String getPermissionGroupName(String permission) {
        try {
            PackageManager manager = CONTEXT.getPackageManager();
            if (manager == null) {
                return null;
            }
            PermissionInfo pInfo = manager.getPermissionInfo(permission, 0);
            if (pInfo == null) {
                return null;
            }

            //group 为null，直接使用权限本身的lable
            if (pInfo.group == null) {
                return pInfo.loadLabel(manager).toString();
            }

            PermissionGroupInfo gInfo = manager.getPermissionGroupInfo(pInfo.group, 0);
            if (gInfo == null) {
                return null;
            }
            return gInfo.loadLabel(manager).toString();
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Cannot find permission group:" + permission);
        }
        return null;
    }

    /**
     * [是否M版本]
     *
     * @return true:是
     */
    public static boolean isMNC() {
        return Build.VERSION.SDK_INT >= 23;
    }

}
