package com.yaneryi.permissionutil.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.LinearLayout;

import java.util.Map;

/**
 * View工具类
 * Created by clude on 2017/11/30.
 */

public final class ViewUtils {

    /**
     * 私有构造函数
     */
    private ViewUtils() {

    }

    /**
     * 设置view的显示状态
     *
     * @param view       需要设置的view
     * @param visibility 显示状态
     */
    public static void setVisibility(View view, int visibility) {
        if (null != view) {
            view.setVisibility(visibility);
        }
    }

    /**
     * 设置view的显示状态
     *
     * @param view         需要设置的view
     * @param isVisibility true:View.VISIBLE false:View.GONE
     */
    public static void setVisibility(View view, boolean isVisibility) {
        if (null != view) {
            view.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置view的enable状态
     *
     * @param view    需要设置的View
     * @param enabled 是否可点击
     */
    public static void setEnabled(View view, boolean enabled) {
        if (null != view) {
            view.setEnabled(enabled);
        }
    }

    /**
     * 设置MenuItem的enable状态
     *
     * @param menuItem 需要设置的MenuItem
     * @param enabled  是否可点击
     */
    public static void setEnabled(MenuItem menuItem, boolean enabled) {
        if (null != menuItem) {
            menuItem.setEnabled(enabled);
        }
    }

    /**
     * 设置MenuItem的Icon
     *
     * @param menuItem 需要设置的MenuItem
     * @param iconRes  Icon资源id
     */
    public static void setIcon(MenuItem menuItem, int iconRes) {
        if (null != menuItem) {
            menuItem.setIcon(iconRes);
        }
    }

    /**
     * 设置View背景
     *
     * @param view       设置View
     * @param drawableId 背景资源Id
     */
    @SuppressWarnings("deprecation")
    public static void setBackgroundDrawable(View view, int drawableId) {
        if (null != view) {
            view.setBackgroundDrawable(ResUtils.getDrawable(drawableId));
        }
    }

    /**
     * 设置View背景
     *
     * @param view     设置View
     * @param drawable 背景资源drawable
     */
    @SuppressWarnings("deprecation")
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (null != view) {
            view.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置View背景
     *
     * @param view  View
     * @param resId 资源id
     */
    public static void setBackgroundResource(View view, int resId) {
        if (null != view) {
            view.setBackgroundResource(resId);
        }
    }

    /**
     * 判断一个View是否可见
     *
     * @param view 视图
     * @return 是否可见
     */
    public static boolean isVisibility(View view) {
        return null != view && view.getVisibility() == View.VISIBLE;
    }

    /**
     * Applies a tint to the background drawable. Does not modify the current tint mode, which is
     * {@link PorterDuff.Mode#SRC_IN} by default.
     * API level 21
     *
     * @param view 视图
     * @param tint the tint to apply, may be {@code null} to clear tint
     */
    @SuppressLint("NewApi")
    public static void setBackgroundTintList(View view, ColorStateList tint) {
        if (null != view && Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(tint);
        }
    }

    /**
     * Look for a child view with the given id. If this view has the given id, return this view.
     *
     * @param view parentview
     * @param id   The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(View view, int id) {
        if (null != view) {
            return (T) view.findViewById(id);
        }

        return null;
    }

    /**
     * Look for a child view with the given id. If this view has the given id, return this view.
     *
     * @param activity activity
     * @param id       The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(Activity activity, int id) {
        if (null != activity) {
            return (T) activity.findViewById(id);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends LayoutParams> T getLayoutParams(View view) {
        if (null != view) {
            return (T) view.getLayoutParams();
        }

        return null;
    }

    public static void setLayoutParams(View view, LayoutParams params) {
        if (null != view) {
            view.setLayoutParams(params);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends android.app.Fragment> T findFragmentByTag(android.app.FragmentManager mFragmentManager,
                                                                       String tag) {
        if (null != mFragmentManager) {
            return (T) mFragmentManager.findFragmentByTag(tag);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewInflateById(LayoutInflater mInflater, int id) {
        if (null != mInflater) {
            return (T) mInflater.inflate(id, null);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T getChildAt(LinearLayout navLayout, int id) {
        if (null != navLayout) {
            return (T) navLayout.getChildAt(id);
        }

        return null;
    }

    /**
     * 将view添加到新的parent上
     *
     * @param child
     * @param newParent 新的parent
     */
    public static void adjustParent(View child, ViewGroup newParent) {
        if (null != child && null != newParent) {
            ViewGroup parent = getParent(child);
            if (parent != null) {
                parent.removeView(child);
            }
            newParent.addView(child);
        }
    }

    /**
     * 获得一个view的parent
     *
     * @param child
     * @return
     */
    public static ViewGroup getParent(View child) {
        ViewGroup parent = null;

        if (child != null) {
            ViewParent p = child.getParent();
            if (p != null && p instanceof ViewGroup) {
                parent = (ViewGroup) p;
            }
        }
        return parent;

    }

    /**
     * 拷贝一个view的内容到bitmap上
     *
     * @param view
     * @param isConsiderAlpha 是否需要考虑alpha通道，如果考虑，可能会比较耗时
     * @return
     */
    public Bitmap getViewBitmap(View view, boolean isConsiderAlpha) {
        Bitmap bitmap = null;
        if (view != null) {
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                    isConsiderAlpha ? Config.ARGB_8888 : Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
        }
        return bitmap;
    }

    /**
     * Call this method to remove all child views from the ViewGroup.
     */
    public static void removeAllViews(ViewGroup viewGroup) {
        if (null != viewGroup) {
            viewGroup.removeAllViews();
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        Context context = EnvironmentEx.getApplicationContext();
        return dip2px(context, dpValue);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        Context context = EnvironmentEx.getApplicationContext();
        return px2dip(context, pxValue);
    }

    /**
     * 获取SharedPreferences中的指定键的值
     *
     * @param context 上下文
     * @param key     键
     * @param defVal  默认值
     * @return
     */
    public static Object getSPData(Context context, String key, Object defVal) {
        SharedPreferences sp = context.getSharedPreferences("recommended-data", Context.MODE_PRIVATE);
        // 获取所有SharedPreferences中保存的键值对，保存到map中
        Map<String, ?> map = sp.getAll();
        // 通过键获取值
        Object value = map.get(key);
        // 如果获取的值不为null则返回获取到的值
        if (value != null) {
            return value;
        }
        // 如果获取的值为null则返回默认值
        return defVal;
    }

    /**
     * 保存键值对数据到SharedPreferences中
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void saveSPData(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences("recommended-data", Context.MODE_PRIVATE);
        // 获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 保存数据
        saveSPData(editor, key, value);
        // 提交
        editor.commit();
    }

    /**
     * 将键值对保存到编辑器中
     *
     * @param editor 编辑器
     * @param key    键
     * @param value  值
     */
    private static void saveSPData(SharedPreferences.Editor editor, String key, Object value) {
        // 如果value是Boolean类型 保存Boolean值
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        // 如果value是String类型 保存String值
        if (value instanceof String) {
            editor.putString(key, (String) value);
        }
        // 如果value是Integer类型 保存Integer值
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        // 如果value是Float类型 保存Float值
        if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        }
        // 如果value是Long类型 保存Long值
        if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
    }

}
