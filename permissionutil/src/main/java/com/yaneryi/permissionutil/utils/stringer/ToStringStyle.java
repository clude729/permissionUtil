package com.yaneryi.permissionutil.utils.stringer;

/**
 * 简单对象ToString的格式类
 * Created by clude on 2017/12/1.
 */
public final class ToStringStyle {
    /**
     * 工具类，私有化构造方法
     */
    private ToStringStyle() {

    }

    /**
     * 拼接对象唯一标识
     *
     * @param object 目标对象
     * @return 返回对象的唯一标识字符串
     */
    public static String getObjIdentity(Object object) {
        //判断输入对象是否为null，为null直接返回
        if (null == object) {
            return ToStringKeys.EMPTY;
        }

        //获取全类名和标识标号
        StringBuilder builder = new StringBuilder(50);
        builder.append(object.getClass().getName());
        builder.append(ToStringKeys.AT_TEXT);
        builder.append(Integer.toHexString(System.identityHashCode(object)));
        return builder.toString();
    }

    /**
     * 获取无对象标记的格式
     *
     * @return 无对象标记的格式
     */
    public static ObjectToStringStyle getObjectStyleNoIdentity() {
        //初始化默认格式，不需要唯一标识
        return new ObjectToStringStyle(ToStringKeys.LEFT_SQUARE_BRACKET, ToStringKeys.RIGHT_SQUARE_BRACKET,
                ToStringKeys.COMMA_BLANK, ToStringKeys.EQUAL_BLANK_STR, false);
    }

    private static class BasicStyle {
        /**
         * 空字符串
         */
        private final String nullText;

        /**
         * 内容开始标识
         */
        private final String bodyStart;

        /**
         * 内容结束标识
         */
        private final String bodyEnd;

        /**
         * 元素之间的分割标识
         */
        private final String elementSeparator;

        /**
         * 字符串最大位数
         */
        private int maxStringSize;

        /**
         * 构造方法
         *
         * @param nullText         null文本字符串
         * @param bodyStart        主体开始标识
         * @param bodyEnd          主体结束标识
         * @param elementSeparator 元素之间分隔符号
         */
        public BasicStyle(String nullText, String bodyStart, String bodyEnd, String elementSeparator) {
            super();
            this.nullText = nullText;
            this.bodyStart = bodyStart;
            this.bodyEnd = bodyEnd;
            this.elementSeparator = elementSeparator;
            this.maxStringSize = 0;
        }

        public String getNullText() {
            return nullText;
        }

        public String getBodyStart() {
            return bodyStart;
        }

        public String getBodyEnd() {
            return bodyEnd;
        }

        public String getElementSeparator() {
            return elementSeparator;
        }

        public int getMaxStringSize() {
            return maxStringSize;
        }

        public void setMaxStringSize(int maxStringSize) {
            this.maxStringSize = maxStringSize;
        }
    }

    public static class ObjectToStringStyle extends BasicStyle {
        /**
         * 变量名与变量值的分割符号
         */
        private final String nameValueSeparator;

        /**
         * 是否包括对象唯一标识
         */
        private boolean includeIdentity;

        /**
         * 默认构造函数
         */
        public ObjectToStringStyle() {
            this(ToStringKeys.LINE_SEP + ToStringKeys.LEFT_SQUARE_BRACKET, ToStringKeys.RIGHT_SQUARE_BRACKET,
                    ToStringKeys.COMMA_BLANK, ToStringKeys.EQUAL_BLANK_STR, true);
        }

        /**
         * 对象格式构造函数
         *
         * @param bodyStart          主体开始标识
         * @param bodyEnd            主体结束标识
         * @param elementSeparator   元素之间分隔符号
         * @param nameValueSeparator 变量名与变量值的分割符号
         * @param includeIdentity    是否包括对象唯一标识
         */
        public ObjectToStringStyle(String bodyStart, String bodyEnd, String elementSeparator, String nameValueSeparator,
                                   boolean includeIdentity) {
            super(ToStringKeys.NULL_TEXT, bodyStart, bodyEnd, elementSeparator);
            this.nameValueSeparator = nameValueSeparator;
            this.includeIdentity = includeIdentity;
        }

        public boolean isIncludeIdentity() {
            return includeIdentity;
        }

        public void setIncludeIdentity(boolean includeIdentity) {
            this.includeIdentity = includeIdentity;
        }

        public String getNameValueSeparator() {
            return nameValueSeparator;
        }
    }

    public static class ArrayToStringStyle extends BasicStyle {
        /**
         * 子数组之间的分隔符号
         */
        private final String subArraySeparator;

        /**
         * 默认构造函数
         */
        ArrayToStringStyle() {
            this(ToStringKeys.LEFT_BRACKET, ToStringKeys.RIGHT_BRACKET, ToStringKeys.COMMA_BLANK,
                    ToStringKeys.COMMA_BLANK);
        }

        /**
         * 数组格式构造函数
         *
         * @param bodyStart         主体开始标识
         * @param bodyEnd           主体结束标识
         * @param elementSeparator  元素之间分隔符号
         * @param subArraySeparator 子数组之间的分隔符号
         */
        public ArrayToStringStyle(String bodyStart, String bodyEnd, String elementSeparator, String subArraySeparator) {
            super(ToStringKeys.NULL_TEXT, bodyStart, bodyEnd, elementSeparator);
            this.subArraySeparator = subArraySeparator;
        }

        public String getSubArraySeparator() {
            return subArraySeparator;
        }
    }
}
