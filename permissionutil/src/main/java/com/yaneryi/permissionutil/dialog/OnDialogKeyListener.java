package com.yaneryi.permissionutil.dialog;

import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * 对话框Key事件自定义回调接口
 * Created by clude on 2017/12/11.
 */

public interface OnDialogKeyListener {

    boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event);

}
