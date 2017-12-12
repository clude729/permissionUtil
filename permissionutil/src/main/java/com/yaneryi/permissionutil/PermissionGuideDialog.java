package com.yaneryi.permissionutil;

import android.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yaneryi.permissionutil.dialog.BaseAlertDialog;
import com.yaneryi.permissionutil.dialog.DialogBean;
import com.yaneryi.permissionutil.utils.ResUtils;
import com.yaneryi.permissionutil.utils.ViewUtils;


/**
 * 引导设置开启权限的对话框
 * Created by clude on 2017/12/10.
 */
public class PermissionGuideDialog extends BaseAlertDialog {

    private static final class PermissionGuideDialogBean extends DialogBean {
        private static final long serialVersionUID = -4925374353517258279L;
        private final String[] permissions;

        PermissionGuideDialogBean(String... ps) {
            permissions = ps;
        }
    }

    /**
     * [生成对话框]
     *
     * @param permissions 要展示的权限
     * @return 对话框实例
     */
    public static PermissionGuideDialog newInstance(String... permissions) {
        PermissionGuideDialog dialog = new PermissionGuideDialog();
        PermissionGuideDialogBean dialogBean = new PermissionGuideDialogBean(permissions);
        dialogBean.setTitle(R.string.oper_confirm);
        dialogBean.setPositiveText(R.string.oper_ok);
        dialogBean.setNegativeText(R.string.oper_cancel);
        setArgs(dialog, dialogBean);
        return dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subCreateDialog(Builder builder) {
        // 1.获取视图膨胀器
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        // 2.1从XML文件膨胀视图
        View customView = inflater.inflate(R.layout.dialog_permission_content_layout, null);

        // 2.2初始化视图
        initView(customView);

        // 3.设置对话框视图
        setPadding(customView);
        builder.setView(customView);
    }

    private void initView(View customView) {
        TextView titleTv = ViewUtils.findViewById(customView, R.id.permissions_content_title);
        TextView tv = ViewUtils.findViewById(customView, R.id.permissions_content);

        if (dialogBean instanceof PermissionGuideDialogBean) {
            PermissionGuideDialogBean bean = (PermissionGuideDialogBean) dialogBean;
            String[] orgin = bean.permissions;
            if (orgin != null) {
                int len = orgin.length;
                titleTv.setText(ResUtils.getQuantityString(R.plurals.open_permission_guide, len));
                StringBuilder builder = new StringBuilder();
                int i = 1;
                for (String s : orgin) {
                    builder.append('.').append(PermissionUtils.getPermissionGroupName(s));
                    if (i < len) {
                        builder.append('\n');
                    }
                    i++;
                }
                tv.setText(builder.toString());
            }
        }

    }

}
