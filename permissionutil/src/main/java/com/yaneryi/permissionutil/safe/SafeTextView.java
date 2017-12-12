package com.yaneryi.permissionutil.safe;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.yaneryi.permissionutil.utils.StringUtil;
import com.yaneryi.permissionutil.utils.ViewUtils;

/**
 * TextView工具类
 * Created by clude on 2017/12/2.
 */

public final class SafeTextView {

    /**
     * 私有构造方法
     */
    private SafeTextView() {

    }

    /**
     * 设置TextView、Button的文字
     *
     * @param view 需要设置的View
     * @param text 文字
     */
    public static void setText(TextView view, CharSequence text) {
        if (null != view) {
            view.setText(text);
        }
    }

    /**
     * 设置TextView、Button的文字
     *
     * @param view  需要设置的View
     * @param resId 资源Id
     */
    public static void setText(TextView view, int resId) {
        if (null != view) {
            view.setText(resId);
        }
    }

    /**
     * 设置TextView颜色
     *
     * @param tv    TextView
     * @param color 颜色
     */
    public static void setTextColor(TextView tv, int color) {
        if (null != tv) {
            tv.setTextColor(color);
        }
    }

    /**
     * Sets the Drawables (if any) to appear to the left of, above, to the right of, and below the text. Use
     * {@code null} if you do not want a Drawable there. The Drawables must already have had {@link Drawable#setBounds}
     * called.
     * Calling this method will overwrite any Drawables previously set using {@link #setCompoundDrawables} or
     * related methods.
     *
     * @attr ref android.R.styleable#TextView_drawableLeft
     * @attr ref android.R.styleable#TextView_drawableTop
     * @attr ref android.R.styleable#TextView_drawableRight
     * @attr ref android.R.styleable#TextView_drawableBottom
     */
    public static void setCompoundDrawables(TextView tv, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (null != tv) {
            tv.setCompoundDrawables(left, top, right, bottom);
        }
    }

    /**
     * Sets the Drawables (if any) to appear to the left of, above, to the right of, and below the text. Use 0 if you do
     * not want a Drawable there. The Drawables' bounds will be set to their intrinsic bounds.
     *
     * @param tv     TextView
     * @param left   Resource identifier of the left Drawable.
     * @param top    Resource identifier of the top Drawable.
     * @param right  Resource identifier of the right Drawable.
     * @param bottom Resource identifier of the bottom Drawable.
     */
    public static void setCompoundDrawablesWithIntrinsicBounds(TextView tv, int left, int top, int right, int bottom) {
        if (null != tv) {
            tv.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    /**
     * 设置TextView的文字
     *
     * @param textViewTitle   textView的标题
     * @param textViewContent textView的内容
     * @param contentValue    textView内容的值
     */
    public static void setText(TextView textViewTitle, TextView textViewContent, String contentValue) {
        if (!StringUtil.isEmpty(contentValue)) {
            setText(textViewContent, contentValue);
        } else {
            //数据为空，则将view隐藏
            ViewUtils.setVisibility(textViewTitle, View.GONE);
            ViewUtils.setVisibility(textViewContent, View.GONE);
        }
    }

    /**
     * Sets the size of the padding between the compound drawables and the text.
     *
     * @param tv  TextView
     * @param pad the padding between the compound drawables and the text
     */
    public static void setCompoundDrawablePadding(TextView tv, int pad) {
        if (null != tv) {
            tv.setCompoundDrawablePadding(pad);
        }
    }

}
