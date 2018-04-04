package com.lh.accountbook.service.apprun;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by LuHao on 2017/6/20.
 */

public class ResidentUtil {

    /**
     * 判断进程是否运行
     *
     * @return
     */
    public static boolean isProessRunning(Context context, String proessName) {

        boolean isRunning = false;
        //获取服务
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取正在运行的程序
        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : lists) {
            //如果进程名称等于目标名称，则说明在运行
            if (info.processName.equals(proessName)) {
                isRunning = true;
            }
        }
        return isRunning;
    }
}
