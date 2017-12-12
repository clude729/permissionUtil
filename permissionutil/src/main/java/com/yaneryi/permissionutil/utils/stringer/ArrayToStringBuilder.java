package com.yaneryi.permissionutil.utils.stringer;

/**
 * 数组对象ToString构建工具类
 * Created by clude on 2017/12/2.
 */

public class ArrayToStringBuilder {

    /**
     * 数组转字符串的格式对象
     */
    private final ToStringStyle.ArrayToStringStyle arrayStyle;

    /**
     * 构造器对象
     */
    private final StringBuilder builder;

    /**
     * 默认构造函数
     */
    public ArrayToStringBuilder() {
        this(new ToStringStyle.ArrayToStringStyle());
    }

    /**
     * 数组转字符串构造方法
     *
     * @param arrayStyle 数组转字符串格式
     */
    public ArrayToStringBuilder(ToStringStyle.ArrayToStringStyle arrayStyle) {
        this.arrayStyle = null == arrayStyle ? new ToStringStyle.ArrayToStringStyle() : arrayStyle;
        this.builder = new StringBuilder(512);
    }

    /**
     * 返回核心字符串Builder
     *
     * @return 返回核心字符串Builder
     */
    StringBuilder getCoreBuilder() {
        return builder;
    }

    /**
     * 拼接对象数组元素
     *
     * @param objArray 对象数组元素
     */
    public void append(Object[] objArray) {
        //判断属性值是否为空
        if (null == objArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        boolean isElementArray = false;
        for (int i = 0; i < objArray.length; i++) {
            //判断对象元素是否为数组类型
            if (!isElementArray && null != objArray[i]) {
                isElementArray = objArray[i].getClass().isArray();
            }

            if (i > 0) {
                appendValue(isElementArray ? arrayStyle.getSubArraySeparator() : arrayStyle.getElementSeparator());
            }

            //判断元素取值为空则拼接空字符串
            if (null == objArray[i]) {
                appendValue(arrayStyle.getNullText());
            } else {
                append(objArray[i]);
            }
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 拼接属性值
     *
     * @param object 属性值
     */
    public void append(Object object) {
        //判断属性值是否为空
        if (null == object) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //判断各种简单类型数组类型
        if (object instanceof byte[]) {
            append((byte[]) object);
        } else if (object instanceof char[]) {
            append((char[]) object);
        } else if (object instanceof short[]) {
            append((short[]) object);
        } else if (object instanceof int[]) {
            append((int[]) object);
        } else if (object instanceof long[]) {
            append((long[]) object);
        } else if (object instanceof float[]) {
            append((float[]) object);
        } else if (object instanceof double[]) {
            append((double[]) object);
        } else if (object instanceof boolean[]) {
            append((boolean[]) object);
        }
        //判断属性值是否为其他类型的数组
        else if (object.getClass().isArray()) {
            append((Object[]) object);
        }
        //其他非数组情况直接拼接对象的值
        else {
            appendValue(object);
        }
    }

    /**
     * 数组（byte）拼接对象取值
     *
     * @param byteArray 数组值
     */
    public void append(byte[] byteArray) {
        //判断属性值是否为空
        if (null == byteArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < byteArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }
            appendValue(byteArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（char）拼接对象取值
     *
     * @param charArray 数组值
     */
    public void append(char[] charArray) {
        //判断属性值是否为空
        if (null == charArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < charArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(charArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（short）拼接对象取值
     *
     * @param shortArray 数组值
     */
    public void append(short[] shortArray) {
        //判断属性值是否为空
        if (null == shortArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < shortArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(shortArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（Int）拼接对象取值
     *
     * @param intArray 数组值
     */
    public void append(int[] intArray) {
        //判断属性值是否为空
        if (null == intArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < intArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(intArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（Long）拼接对象取值
     *
     * @param longArray 数组值
     */
    public void append(long[] longArray) {
        //判断属性值是否为空
        if (null == longArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < longArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(longArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（float）拼接对象取值
     *
     * @param floatArray 数组值
     */
    public void append(float[] floatArray) {
        //判断属性值是否为空
        if (null == floatArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < floatArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(floatArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（double）拼接对象取值
     *
     * @param doubleArray 数组值
     */
    public void append(double[] doubleArray) {
        //判断属性值是否为空
        if (null == doubleArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < doubleArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(doubleArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 数组（boolean）拼接对象取值
     *
     * @param booleanArray 数组值
     */
    public void append(boolean[] booleanArray) {
        //判断属性值是否为空
        if (null == booleanArray) {
            appendValue(arrayStyle.getNullText());
            return;
        }

        //拼接数组开始标识
        appendValue(arrayStyle.getBodyStart());

        //拼接数组各个元素
        for (int i = 0; i < booleanArray.length; i++) {
            if (i > 0) {
                appendValue(arrayStyle.getElementSeparator());
            }

            appendValue(booleanArray[i]);
        }

        //拼接数组结束标识
        appendValue(arrayStyle.getBodyEnd());
    }

    /**
     * 拼接byte型值
     *
     * @param byteValue byte型值
     */
    private void appendValue(byte byteValue) {
        if (canBuilderAppend()) {
            builder.append(byteValue);
        }
    }

    /**
     * 拼接char型值
     *
     * @param charValue char型值
     */
    private void appendValue(char charValue) {
        if (canBuilderAppend()) {
            builder.append(charValue);
        }
    }

    /**
     * 拼接short型值
     *
     * @param shortValue short型值
     */
    private void appendValue(short shortValue) {
        if (canBuilderAppend()) {
            builder.append(shortValue);
        }
    }

    /**
     * 拼接整型值
     *
     * @param intValue 整型值
     */
    private void appendValue(int intValue) {
        if (canBuilderAppend()) {
            builder.append(intValue);
        }
    }

    /**
     * 拼接long型值
     *
     * @param longValue long型值
     */
    private void appendValue(long longValue) {
        if (canBuilderAppend()) {
            builder.append(longValue);
        }
    }

    /**
     * 拼接float型值
     *
     * @param floatValue float型值
     */
    private void appendValue(float floatValue) {
        if (canBuilderAppend()) {
            builder.append(floatValue);
        }
    }

    /**
     * 拼接double型值
     *
     * @param doubleValue double型值
     */
    private void appendValue(double doubleValue) {
        if (canBuilderAppend()) {
            builder.append(doubleValue);
        }
    }

    /**
     * 拼接布尔值
     *
     * @param booleanValue 布尔值
     */
    private void appendValue(boolean booleanValue) {
        if (canBuilderAppend()) {
            builder.append(booleanValue);
        }
    }

    /**
     * 拼接对象值
     *
     * @param objValue 对象值
     */
    private void appendValue(Object objValue) {
        if (canBuilderAppend()) {
            builder.append(objValue);
        }
    }

    /**
     * 判断是否可以拼接信息
     *
     * @return 是否可以继续拼接
     */
    private boolean canBuilderAppend() {
        //获取是否能够继续拼接信息
        int maxSize = arrayStyle.getMaxStringSize();
        boolean canNotAppend = maxSize > 0 && builder.length() >= maxSize;
        //如果不能继续则检查是否需要拼接省略号信息
        if (canNotAppend && !isBuilderEndOf(builder, ToStringKeys.SUSPENSION_POINTS_STR)) {
            builder.append(ToStringKeys.SUSPENSION_POINTS_STR);
        }
        //返回是否能继续拼接
        return !canNotAppend;
    }

    /**
     * 判断是否已经包括特殊字符
     *
     * @param builder0 拼接器
     * @param suffix   后缀标识
     * @return 是否包括
     */
    boolean isBuilderEndOf(StringBuilder builder0, String suffix) {
        //获取两个元素的长度
        int builderLen = null == builder0 ? 0 : builder0.length();
        int suffixLen = null == suffix ? 0 : suffix.length();

        //初始化变量是否包括
        boolean match = false;

        //判断长度是否满足
        if (builderLen > 0 && suffixLen > 0 && builderLen >= suffixLen) {
            match = true;
            for (int i = 0; i < suffixLen; i++) {
                if (builder0.charAt(builderLen - 1 - i) != suffix.charAt(suffixLen - 1 - i)) {
                    match = false;
                    break;
                }
            }
        }

        //是否已经包括
        return match;
    }

    /**
     * 情况构建器对象
     */
    public void clearBuilder() {
        builder.delete(0, builder.length());
    }

    /**
     * 返回拼接对象字符串
     *
     * @return 字符串信息
     */
    public String toString() {
        //返回结束表示
        return builder.toString();
    }

}
