package com.yaneryi.permissionutil.utils;

import java.util.regex.Pattern;

/**
 * 字符串工具
 * Created by clude on 2017/11/30.
 */

public final class StringUtil {

    private static Pattern numberPattern = Pattern.compile("^[\\d]*$");

    public static boolean isEmpty(String str) {
        return str == null?true:str.equals("");
    }

    public static boolean isNumber(String str) {
        return numberPattern.matcher(str).matches();
    }

    private StringUtil() {
    }

}
