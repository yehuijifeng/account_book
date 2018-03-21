package com.lh.accountbook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LuHao on 2018/3/19.
 * 日期工具类
 */

public class DateUtils {
    public static String FORMAT = "yyyy-MM-dd";
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT_CN = "yyyy年MM月dd日";
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    private static String format(Date date, String pattern, Locale locale) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    /**
     * 传入时间戳和固定格式
     *
     * @param time
     * @return
     */
    public static String format(long time, String time_str) {
        return format(new Date(time), time_str, Locale.CHINA);
    }

    /**
     * 传入时间戳
     *
     * @param time
     * @return
     */
    public static String format(long time) {
        return format(new Date(time), FORMAT_LONG, Locale.CHINA);
    }

    /**
     * 获得默认时间格式
     *
     * @return
     */
    public static String getNow() {
        return format(new Date(System.currentTimeMillis()), FORMAT_LONG, Locale.CHINA);
    }

}
