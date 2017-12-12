package com.yaneryi.permissionutil.utils.stringer;

/**
 * 对象转字符串的常理类
 * Created by clude on 2017/12/2.
 */

public final class ToStringKeys {

    /** 字符常量 **/
    /**
     * 左大刮号
     */
    public static final String LEFT_BRACKET = "{";

    /**
     * 右大刮号
     */
    public static final String RIGHT_BRACKET = "}";

    /**
     * 左小刮号
     */
    public static final String LEFT_SMALL_BRACKET = "(";

    /**
     * 右小刮号
     */
    public static final String RIGHT_SMALL_BRACKET = ")";

    /**
     * 左方刮号
     */
    public static final String LEFT_SQUARE_BRACKET = "[";

    /**
     * 右方刮号
     */
    public static final String RIGHT_SQUARE_BRACKET = "]";

    /**
     * 竖线
     */
    public static final String VERTICAL_SEP = "|";

    /**
     * 转义竖线
     */
    public static final String ESCAPE_VERTICAL_SEP = "\\|";

    /**
     * 横线
     */
    public static final String HORIZONTAL_SET = "-";

    /**
     * 下划线
     */
    public static final String UNDER_LINE = "_";

    /**
     * 逗号
     */
    public static final String COMMA_SEP = ",";

    /**
     * 逗号加空格
     */
    public static final String COMMA_BLANK = ", ";

    /**
     * 井号信息
     */
    public static final String JING_HAO = "#";

    /**
     * 空格字符串
     */
    public static final String BLANK = " ";

    /**
     * 空格字符串encode
     */
    public static final String BLANK_ENCODE = "%20";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * null的字符串
     */
    public static final String NULL_STR = "null";

    /**
     * NULL的字符串
     */
    public static final String NULL_TEXT = "<null>";

    /**
     * 等于符号
     */
    public static final String EQUAL_STR = "=";

    /**
     * 等于符号，前后有空格
     */
    public static final String EQUAL_BLANK_STR = " = ";

    /**
     * 系统换行符
     */
    public static final String LINE_SEP = System.getProperty("line.separator");

    /**
     * 星号信息,用来代替密码打印
     */
    public static final String XING_HAO = "******";

    /**
     * 点
     */
    public static final String POINT_STR = ".";

    /**
     * 转义圆点
     */
    public static final String ESCAPE_POINT_STR = "\\.";

    /**
     * 波浪线
     */
    public static final String WAVE_STR = "~";

    /**
     * 冒号标记
     */
    public static final String COLON_STR = ":";

    /**
     * 箭头符号
     */
    public static final String AND_ARROW = "+>";

    /**
     * 省略号标记
     */
    public static final String SUSPENSION_POINTS_STR = "...";

    /**
     * 字符@的字符串
     */
    public static final String AT_TEXT = "@";

    /**
     * NA标记
     */
    public static final String NA = "N/A";

    /**
     * +标记
     */
    public static final String ADD_SIGN = "+";

    /**
     * url地址分割符
     */
    public static final String URL_FENGE = "/";

    /**
     * 英文单引号
     */
    public static final String SINGLE_QUOTATION_US = "'";

    /**
     * 英文单引号
     */
    public static final String DOUBLE_QUOTATION_US = "''";

    /**
     * HTTP
     */
    public static final String QUESTION_MARK = "?";

    /**
     * HTTP
     */
    public static final String AND_MARK = "&";

    /**
     * char的默认值
     */
    public static final char NULL_CHAR = 0;

    /**
     * 单个双引号
     */
    public static final String SINGLE_QUOTATION_CN = "\"";

    /**
     * 数据库语句拼接符：AND
     */
    public static final String DB_AND = " AND ";

    /**
     * 数据库语句拼接符：?
     */
    public static final String DB_QUESTION = "?";

    /**
     * 数据库语句拼接符：<>
     */
    public static final String DB_NOT_EQUAL = " <> ";

    /**
     * 数据库语句：count(*)
     */
    public static final String DB_COUNT_STAR = "count(*)";

    /**
     * 数据库语句：desc
     */
    public static final String DB_DESC = " desc";

    /**
     * 常理类，私有化构造方法
     */
    private ToStringKeys() {

    }

}
