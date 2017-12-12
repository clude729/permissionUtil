package com.yaneryi.permissionutil.dialog;

import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * Created by clude on 2017/12/11.
 */

public interface OnDialogKeyListener {

    boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event);

}
