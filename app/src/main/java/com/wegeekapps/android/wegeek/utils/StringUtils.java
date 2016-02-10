package com.wegeekapps.android.wegeek.utils;

import android.content.Context;

/**
 * 字符串处理工具类
 */
public class StringUtils {
    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 是否不这空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * 是否空白
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static String formStr(Context context, int resId, Object... obj) {
        String res = context.getResources().getString(resId);
        return String.format(res, obj);
    }

}
