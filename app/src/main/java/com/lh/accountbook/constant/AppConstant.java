package com.lh.accountbook.constant;

import android.os.Environment;

/**
 * Created by LuHao on 2018/3/19.
 * app常量
 */

public class AppConstant {
    /**
     * 防止被实例化
     */
    private AppConstant() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //日志
    public static final String TAG = "appjson";

    //sd卡根路径
    public static String getSDPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    //app根目录
    public static final String APP_PATH = getSDPath() + "/AccountBook/";
    //app日志目录
    public static final String APP_PATH_LOG = APP_PATH + "/LOG/";
}
