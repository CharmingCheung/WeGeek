package com.wegeekapps.android.wegeek.utils;

import android.util.Log;

import com.wegeekapps.android.wegeek.BuildConfig;

/**
 * Created by Charming on 15/10/28.
 */
public class PrintUtils {

    /**
     * 打印
     *
     * @param text
     */
    public static void print(String text) {
        if (BuildConfig.DEBUG) System.out.print(text);
    }

    /**
     * 打印 分行
     *
     * @param text
     */
    public static void println(String text) {
        if (BuildConfig.DEBUG) System.out.println(text);
    }

    /**
     * 打印日志
     *
     * @param text
     */
    public static void log(String text) {
        if (BuildConfig.DEBUG) Log.v("DEBUG", text);
    }


}
