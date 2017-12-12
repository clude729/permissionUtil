package com.yaneryi.permissionutil.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

/**
 * 提示对话框基类
 * Created by clude on 2017/12/10.
 */

public class BaseAlertDialog extends BaseDialog{

    /**
     * 对话框自定义点击回调
     */
    protected OnDialogClickListener onDialogClickListener;

    /**
     * 对话框Key事件自定义回调
     */
    private OnDialogKeyListener onOuterDialogKeyListener;

    /**
     * AlertDialog自带Key事件回调 
     */
    private OnKeyListener onSysKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (null != onOuterDialogKeyListener) {
                return onOuterDialogKeyListener.onKey(dialog, keyCode, event);
            }
            return false;
        }
    };

    /**
     * AlertDialog自带点击回调 
     * 这里转换为自定义点击回调，方便使用
     */
    private DialogInterface.OnClickListener onSysClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            onSystemClicked(which);
        }
    };

    /**
     * 获取对话框新的实例
     *
     * @param dialogBean 对话框配置参数
     * @return 对话框新的实例
     */
    public static BaseAlertDialog newInstance(DialogBean dialogBean) {
        BaseAlertDialog dialog = new BaseAlertDialog();
        setArgs(dialog, dialogBean);
        return dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 处理意外销毁后恢复场景
        doSaveInstance(savedInstanceState);

        // 解析参数
        parseArgs();

        // 构建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setOnKeyListener(onSysKeyListener);

        if (null != dialogBean) {
            // 设置标题
            if (!TextUtils.isEmpty(dialogBean.getTitle())) {
                builder.setTitle(dialogBean.getTitle());
            }

            // 设置提示信息
            if (!TextUtils.isEmpty(dialogBean.getMessage())) {
                builder.setMessage(dialogBean.getMessage());
            }

            // 设置对话框按钮
            setDialogButton(builder);

            // 设置是否可以取消
            // 【注：此处使用DialogFragment setCancelable，使用Dialog的无效】
            setCancelable(dialogBean.isCancelable());

            // 调用子类对话框实现细节
            subCreateDialog(builder);
        }

        // 返回构建好的对话框
        return builder.create();
    }

    /**
     * 子类实现创建对话框其他细节
     *
     * @param builder 对话框构建器
     */
    public void subCreateDialog(AlertDialog.Builder builder) {

    }

    /**
     * 设置对话框点击回调
     *
     * @param listener 点击回调
     */
    public void setOnDialogClickListener(OnDialogClickListener listener) {
        this.onDialogClickListener = listener;
    }

    /**
     * 设置对话框Key事件回调
     *
     * @param listener 回调
     */
    public void setOnDialogKeyListener(OnDialogKeyListener listener) {
        this.onOuterDialogKeyListener = listener;
    }

    /**
     * 设置对话框按钮
     *
     * @param builder 对话框构建器
     */
    private void setDialogButton(AlertDialog.Builder builder) {
        // 设置Positive按钮
        if (!TextUtils.isEmpty(dialogBean.getPositiveText())) {
            builder.setPositiveButton(dialogBean.getPositiveText(), onSysClickListener);
        }

        // 设置Neutral按钮
        if (!TextUtils.isEmpty(dialogBean.getNeutralText())) {
            builder.setNeutralButton(dialogBean.getNeutralText(), onSysClickListener);
        }

        // 设置Negative按钮
        if (!TextUtils.isEmpty(dialogBean.getNegativeText())) {
            builder.setNegativeButton(dialogBean.getNegativeText(), onSysClickListener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        onDialogClickListener = null;
        onOuterDialogKeyListener = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        onDialogClickListener = null;
        onOuterDialogKeyListener = null;
    }

    /**
     * [点击了系统按键]
     *
     * @param which 哪个按钮
     */
    protected void onSystemClicked(int which) {
        switch (which) {
            case AlertDialog.BUTTON_POSITIVE:
                if (null != onDialogClickListener) {
                    onDialogClickListener.onPositive();
                }
                break;

            case AlertDialog.BUTTON_NEUTRAL:
                if (null != onDialogClickListener) {
                    onDialogClickListener.onNeutral();
                }
                break;

            case AlertDialog.BUTTON_NEGATIVE:
                if (null != onDialogClickListener) {
                    onDialogClickListener.onNegative();
                }
                break;
            default:
                break;
        }
    }

}
