package com.yaneryi.permissionutil.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/**
 * 资源工具类
 * Created by clude on 2017/11/30.
 */

public final class ResUtils {

    /**
     * 私有构造方法
     */
    private ResUtils() {

    }

    /**
     * 获取字符串资源
     *
     * @param resId 字符串资源id
     * @return 字符串
     */
    public static String getString(int resId) {
        return EnvironmentEx.getApplicationContext().getString(resId);
    }

    /**
     * 获取字符串资源,可替换字符串
     *
     * @param resId 字符串资源id
     * @param formatArgs 替换字符串
     * @return 字符串
     */
    public static String getString(int resId, Object... formatArgs) {
        return EnvironmentEx.getApplicationContext().getString(resId, formatArgs);
    }

    /**
     * 获取复数字符资源
     *
     * @param resId 资源ID
     * @param quantity zero、one、 two、few、many和other
     * @param formatArgs format替换参数
     * @return String 目标字符串
     */
    public static String getQuantityString(int resId, int quantity, Object... formatArgs) {
        return EnvironmentEx.getApplicationContext().getResources().getQuantityString(resId, quantity, formatArgs);
    }

    /**
     * 获取尺寸资源 
     *
     * @param resId 尺寸资源id
     * @return 尺寸资源
     */
    public static int getDimensionPixelSize(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取尺寸资源 
     *
     * @param resId 尺寸资源id
     * @return 尺寸资源
     */
    public static float getDimension(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getDimension(resId);
    }

    /**
     * 获取尺寸资源
     *
     * @param resId 尺寸资源id
     * @return 尺寸资源
     */
    public static float getFloat(int resId) {
        TypedValue outValue = new TypedValue();
        EnvironmentEx.getApplicationContext().getResources().getValue(resId, outValue, true);
        return outValue.getFloat();
    }

    /**
     * 获取颜色资源
     *
     * @param resId 颜色资源id
     * @return 颜色资源
     */
    public static int getColor(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getColor(resId);
    }

    /**
     * 获取数值
     *
     * @param resId 资源id
     * @return 对应的数值
     */
    public static int getInt(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getInteger(resId);
    }

    /**
     * 获取Drawable
     *
     * @param resId 资源Id
     * @return Drawable
     */
    public static Drawable getDrawable(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getDrawable(resId);
    }

    /**
     * 获取字符串数组
     *
     * @param resId 资源Id
     * @return String[]
     */
    public static String[] getStringArray(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getStringArray(resId);
    }

    /**
     * 获取ColorStateList
     *
     * @param resId 资源Id
     * @return ColorStateList
     */
    public static ColorStateList getColorStateList(int resId) {
        return EnvironmentEx.getApplicationContext().getResources().getColorStateList(resId);
    }
    
}
