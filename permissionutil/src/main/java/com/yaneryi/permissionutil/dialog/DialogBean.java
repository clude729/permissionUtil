package com.yaneryi.permissionutil.dialog;

import com.yaneryi.permissionutil.utils.ResUtils;
import com.yaneryi.permissionutil.utils.stringer.ToStringBuilder;
import com.yaneryi.permissionutil.utils.stringer.ToStringStyle;

import java.io.Serializable;

/**
 * 对话框信息设置数据类
 * Created by clude on 2017/11/30.
 */

public class DialogBean implements Serializable {

    /**
     * bundle传值的Key值
     */
    public static final String KEY_BUNDLE = "DialogBean";

    /**
     * 序列化Id
     */
    private static final long serialVersionUID = 2892118707300730417L;

    /**
     * 对话框标题
     */
    private String title;

    /**
     * 对话框提示信息
     */
    private String message;

    /**
     * positive按钮内容
     */
    private String positiveText;

    /**
     * neutral按钮内容
     */
    private String neutralText;

    /**
     * negative按钮内容
     */
    private String negativeText;

    /**
     * 是否可以取消
     */
    private boolean cancelable = true;

    /**
     * 获取对象ToString生成器对象
     *
     * @return ToString生成器对象
     */
    protected ToStringBuilder getToStringBuilder() {
        return new ToStringBuilder(this, ToStringStyle.getObjectStyleNoIdentity()).append("title", this.title)
                .append("message", this.message).append("positiveText", this.positiveText)
                .append("neutralText", this.neutralText).append("negativeText", this.negativeText)
                .append("cancelable", this.cancelable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getToStringBuilder().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(int resId) {
        title = ResUtils.getString(resId);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(int resId) {
        message = ResUtils.getString(resId);
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public void setPositiveText(int resId) {
        positiveText = ResUtils.getString(resId);
    }

    public String getNeutralText() {
        return neutralText;
    }

    public void setNeutralText(String neutralText) {
        this.neutralText = neutralText;
    }

    public void setNeutralText(int resId) {
        neutralText = ResUtils.getString(resId);
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public void setNegativeText(int resId) {
        negativeText = ResUtils.getString(resId);
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

}
