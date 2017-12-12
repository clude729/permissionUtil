package com.yaneryi.permissionutil.utils.stringer;

import com.yaneryi.permissionutil.utils.StringUtil;

/**
 * 简单对象ToString构建工具类
 * Created by clude on 2017/12/1.
 */
public class ToStringBuilder {
    /**
     * 简单对象字符串格式
     */
    private final ToStringStyle.ObjectToStringStyle objStyle;

    /**
     * 初始化数组String构建器对象
     */
    private final ArrayToStringBuilder arrayToStringBuilder;

    /**
     * 初始化一个连接器对象
     */
    private final StringBuilder builder;

    /**
     * 构造方法
     *
     * @param destObject 对象
     */
    public ToStringBuilder(Object destObject) {
        this(destObject, new ToStringStyle.ObjectToStringStyle());
    }

    /**
     * 构造函数
     *
     * @param destObject 待处理的对象
     * @param objStyle   转换字符串的格式
     */
    public ToStringBuilder(Object destObject, ToStringStyle.ObjectToStringStyle objStyle) {
        //对对象格式进行赋值
        this.objStyle = null == objStyle ? new ToStringStyle.ObjectToStringStyle() : objStyle;

        //初始化一个数组转字符串的Builder
        this.arrayToStringBuilder = new ArrayToStringBuilder();
        //将数组转换器中的拼接对象设置为当前的拼接对象
        this.builder = arrayToStringBuilder.getCoreBuilder();

        //判断是否需要进行对象的唯一标识拼接
        if (this.objStyle.isIncludeIdentity()) {
            String objIdentity = ToStringStyle.getObjIdentity(destObject);
            if (!StringUtil.isEmpty(objIdentity)) {
                this.builder.append(objIdentity);
            }
        }

        //拼接对象的开始字符串
        this.builder.append(this.objStyle.getBodyStart());
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param objValue  属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, Object objValue) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        arrayToStringBuilder.append(objValue);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param objArray  属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, Object[] objArray) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        arrayToStringBuilder.append(objArray);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param byteValue 属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, byte byteValue) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        builder.append(byteValue);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param byteArray 属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, byte[] byteArray) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        arrayToStringBuilder.append(byteArray);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param charValue 属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, char charValue) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        builder.append(charValue);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param charArray 属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, char[] charArray) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        arrayToStringBuilder.append(charArray);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param intValue  属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, int intValue) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        builder.append(intValue);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param intArray  属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, int[] intArray) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        arrayToStringBuilder.append(intArray);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param longValue 属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, long longValue) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        builder.append(longValue);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 拼接属性名称及值
     *
     * @param fieldName 属性名称
     * @param longArray 属性值
     * @return 拼接后的拼接器
     */
    public ToStringBuilder append(String fieldName, long[] longArray) {
        //拼接开始表示
        appendFiledStart(fieldName);

        //拼接字符串属性值
        arrayToStringBuilder.append(longArray);

        //拼接属性结束信息
        appendFiledEnd();

        //返回当期对象
        return this;
    }

    /**
     * 属性值开始信息
     *
     * @param name 属性名称
     */
    private void appendFiledStart(String name) {
        //拼接开始表示
        builder.append(name).append(objStyle.getNameValueSeparator());
    }

    /**
     * 属性值结束信息
     */
    private void appendFiledEnd() {
        //拼接字符串结束标识
        builder.append(objStyle.getElementSeparator());
    }

    /**
     * 返回拼接对象字符串
     *
     * @return 获取处理对象的字符串
     */
    public String toString() {
        //判断是否已经进行属性拼接，如果已经拼接则进行去末尾标识
        String elementSeparator = objStyle.getElementSeparator();
        if (arrayToStringBuilder.isBuilderEndOf(builder, elementSeparator)) {
            builder.setLength(builder.length() - elementSeparator.length());
        }

        //拼接内容结束标识
        builder.append(objStyle.getBodyEnd());

        //返回结束表示
        return builder.toString();
    }
}
