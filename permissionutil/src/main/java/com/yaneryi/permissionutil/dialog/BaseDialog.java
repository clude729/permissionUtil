package com.yaneryi.permissionutil.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yaneryi.permissionutil.R;
import com.yaneryi.permissionutil.utils.ResUtils;

import java.util.UUID;

/**
 * 对话框基类
 * Created by clude on 2017/12/11.
 */

public class BaseDialog extends DialogFragment {

    /**
     * 日志标签
     */
    private static final String TAG = "BaseDialog";

    /**
     * 对话框配置参数
     */
    protected DialogBean dialogBean;

    /**
     * 对话框标识
     */
    private String tag;

    /**
     * 对话框自定义消失回调
     */
    private OnDialogDismissListener onDismissListener;

    /**
     * 构造方法
     */
    public BaseDialog() {
        // 生成对话框标识
        this.tag = UUID.randomUUID().toString();
    }

    /**
     * [添加参数]
     *
     * @param dialog     对话框
     * @param dialogBean 参数
     */
    protected static void setArgs(BaseDialog dialog, DialogBean dialogBean) {
        Bundle args = new Bundle();
        args.putSerializable(DialogBean.KEY_BUNDLE, dialogBean);
        dialog.setArguments(args);
    }

    /**
     * [设置view的左右间隔]
     *
     * @param view 需要设置布局的view
     */
    protected void setPadding(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP || view == null) {
            return;
        }

        int paddingStartAndRightPX = ResUtils.getDimensionPixelSize(R.dimen.dialog_padding);
        view.setPadding(paddingStartAndRightPX, 0, paddingStartAndRightPX, 0);
    }

    /**
     * 设置对话框消失回调
     *
     * @param listener 对话框消失回调
     */
    public void setOnDismissListener(OnDialogDismissListener listener) {
        this.onDismissListener = listener;
    }

    /**
     * 展示对话框
     *
     * @param activity 活动
     */
    public void show(Activity activity) {
        if (null != activity && !activity.isFinishing()) {
            try {
                show(activity.getFragmentManager(), tag);
            } catch (Exception e) {
                Log.e(TAG, "show dialog failed.", e);
            }
        }
    }

    /**
     * 展示对话框
     *
     * @param activity 活动
     * @param tag0     tag信息
     */
    public void show(Activity activity, String tag0) {
        if (null != activity && !activity.isFinishing() && !TextUtils.isEmpty(tag0)) {
            if (activity.getFragmentManager().findFragmentByTag(tag0) != null) {
                return;
            }

            try {
                show(activity.getFragmentManager(), tag0);
            } catch (Exception e) {
                Log.e(TAG, TAG, e);
            }
        }
    }

    /**
     * 销毁对话框
     */
    @Override
    public void dismiss() {
        try {
            if (null != getFragmentManager()) {
                super.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            Log.e(TAG, TAG, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (null != this.onDismissListener) {
            this.onDismissListener.onDismiss();
            this.onDismissListener = null;
        }
    }

    /**
     * 处理保存的状态信息
     *
     * @param savedInstanceState 保存的状态信息
     */
    protected void doSaveInstance(Bundle savedInstanceState) {
        // 处理意外销毁后恢复场景
        if (null != savedInstanceState) {
            try {
                // 这种场景是，界面被意外销毁后恢复，此时不展示对话框碎片，因为外部设置的点击回调等已经为null
                getActivity().getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
            } catch (Exception e) {
                Log.e(TAG, TAG, e);
            }
        }
    }

    /**
     * 解析参数
     */
    protected void parseArgs() {
        Bundle bundle = getArguments();
        if (null == bundle) {
            throw new IllegalArgumentException(TAG + "Arguments bundle cannot be null!");
        }

        dialogBean = (DialogBean) bundle.getSerializable(DialogBean.KEY_BUNDLE);

        // 配置参数如果为null，则对话框构建没有任何意义，直接抛异常
        if (null == dialogBean) {
            throw new IllegalArgumentException(TAG + "Arguments DialogBean cannot be null!");
        }
    }
    
}
