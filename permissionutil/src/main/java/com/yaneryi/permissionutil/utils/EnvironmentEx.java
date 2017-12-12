package com.yaneryi.permissionutil.utils;

import android.content.Context;
import android.view.ContextThemeWrapper;

import com.yaneryi.permissionutil.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统环境全局属性信息
 * Created by clude on 2017/11/29.
 */

public final class EnvironmentEx {

    /**
     * 测试模式
     */
    public static final String KEY_DEBUG_MODE = "debug_mode";

    /**
     * 应用上下文对象
     */
    private static final String KEY_APPLICATION_CONTEXT = "application_context";

    /**
     * 带主题的应用上下文对象
     */
    private static final String KEY_APPLICATION_THEME_CONTEXT = "application_theme_context";

    /**
     * 系统属性缓存对象
     */
    private static final Map<String, Object> GLOBAL_PROPERTIES = new HashMap<String, Object>();

    /**
     * 工具类，私有化构造方法
     */
    private EnvironmentEx() {

    }

    /**
     * 注册程序对象信息
     *
     * @param context 上下文环境
     */
    public static void registAppContext(Context context) {
        //判断主程序对象是否为null，如果为null，则抛出异常对象
        if (null == context) {
            throw new IllegalArgumentException("context can not be null.");
        }

        //对主要对象进行缓存
        Context appContext = context.getApplicationContext();

        //获取带主题的上下文
        Context themeContext = getThemeContext(appContext);
        GLOBAL_PROPERTIES.put(KEY_APPLICATION_CONTEXT, appContext);
        GLOBAL_PROPERTIES.put(KEY_APPLICATION_THEME_CONTEXT, themeContext);
    }

    /**
     * 存储系统属性信息
     *
     * @param key   参数名称
     * @param value 参数值
     */
    public static void addProperty(String key, Object value) {
        GLOBAL_PROPERTIES.put(key, value);
    }

    /**
     * 获取属性值
     *
     * @param key 键值
     * @return 属性的值
     */
    public static Object getProperty(String key) {
        return GLOBAL_PROPERTIES.get(key);
    }

    /**
     * 获取属性带默认值情况
     *
     * @param <T>      属性值类型
     * @param key      属性的名称
     * @param defValue 属性的默认值
     * @return 属性值
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProperty(String key, T defValue) {
        return GLOBAL_PROPERTIES.containsKey(key) ? (T) GLOBAL_PROPERTIES.get(key) : defValue;
    }

    /**
     * 获取系统上下文对象
     *
     * @return 系统上下文对象
     */
    public static Context getApplicationContext() {
        return (Context) GLOBAL_PROPERTIES.get(KEY_APPLICATION_CONTEXT);
    }

    /**
     * 获取带主题的系统上下文对象
     *
     * @return 带主题的系统上下文对象
     */
    public static Context getApplicationThemeContext() {
        return (Context) GLOBAL_PROPERTIES.get(KEY_APPLICATION_THEME_CONTEXT);
    }

    /**
     * [获取带主题id]<BR>
     *
     * @param appContext 原始上下文
     * @return 主题id
     */
    public static int getAppThemeId(Context appContext) {
        int themeID = appContext.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
        if (themeID == 0) {
            themeID = R.style.Theme_AppCompat;
        }
        return themeID;
    }

    /**
     * [获取带主题的上下文]<BR>
     *
     * @param appContext 原始上下文
     * @return 带主题的上下文
     */
    private static Context getThemeContext(Context appContext) {
        Context themeContext = new ContextThemeWrapper(appContext, R.style.Theme_AppCompat);
        return themeContext;
    }

}
