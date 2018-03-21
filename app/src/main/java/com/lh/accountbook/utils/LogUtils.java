package com.lh.accountbook.utils;

import android.util.Log;

import com.lh.accountbook.BuildConfig;

import static com.lh.accountbook.constant.AppConstant.TAG;

/**
 * Created by LuHao on 2018/3/19.
 * 日志工具类
 */

public class LogUtils {
    /**
     * 防止被实例化
     */
    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //BuildConfig.DEBUG
    private static boolean model = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (model)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (model) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (model) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (model) {
            Log.i(TAG, msg);
        }
    }
    public static void w(String msg) {
        if (model) {
            Log.w(TAG, msg);
        }
    }
}
