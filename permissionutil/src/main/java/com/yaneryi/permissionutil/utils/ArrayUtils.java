package com.yaneryi.permissionutil.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 数组工具类
 * Created by clude on 2017/11/28.
 */

public final class ArrayUtils {

    /**
     * 私有构造方法
     */
    private ArrayUtils() {

    }

    /**
     * String数组是否为空(判断是否为null 或 长度为0)
     * @param str 输入的String数组
     * @return 是否为空 true：为空， false：非空
     */
    public static boolean isEmpty(final String[] str) {
        return (null == str) || (0 == str.length);
    }

    /**
     * 数组是否为空(判断是否为null 或 长度为0)
     * @param array 输入的数组
     * @return 是否为空 true：为空， false：非空
     */
    public static boolean isEmpty(final Object[] array) {
        return (null == array) || (0 == array.length);
    }

    /**
     * 数组是否为空(判断是否为null 或 长度为0)
     * @param collection 输入集合对象
     * @return 是否为空 true：为空， false：非空
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * MAP是否为空(判断是否为null 或 长度为0)
     * @param map 输入集合对象
     * @return 是否为空 true：为空， false：非空
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * [判断long数组是否为空]
     * @param array 输入的long数组
     * @return ture:数组为空 false:数组不为空
     */
    public static boolean isEmpty(long[] array) {
        return (null == array) || (0 == array.length);
    }

    /**
     * [判断int数组是否为空]
     * @param array 输入的int数组
     * @return ture:数组为空 false:数组不为空
     */
    public static boolean isEmpty(int[] array) {
        return (null == array) || (0 == array.length);
    }

    /**
     * [判断byte数组是否为空]
     * @param array 输入的byte数组
     * @return ture:数组为空 false:数组不为空
     */
    public static boolean isEmpty(byte[] array) {
        return (null == array) || (0 == array.length);
    }

    /**
     * 截取List
     * @param source 数据源
     * @param start 起始位置
     * @param end 结束位置
     * @return 截取的List
     */
    public static <T> List<T> subList(List<T> source, int start, int end) {
        if (null != source && start >= 0 && end >= start && end <= source.size()) {
            return source.subList(start, end);
        }
        return null;
    }

}
