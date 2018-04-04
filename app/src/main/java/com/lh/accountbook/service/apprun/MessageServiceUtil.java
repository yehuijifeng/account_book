package com.lh.accountbook.service.apprun;

import android.content.Context;

import com.lh.accountbook.BuildConfig;

/**
 * Created by LuHao on 2017/6/20.
 */

public class MessageServiceUtil {

    private final String messageServiceName = BuildConfig.APPLICATION_ID+":AppRunService";

    public void startMessageService(Context context) {
        if (context == null) return;
        if (!ResidentUtil.isProessRunning(context, messageServiceName)) {
//            Intent i = new Intent(context, MessageService.class);
//            context.startService(i);
        }
    }

    public void stopMessageService(Context context) {
        if (context == null) return;
        if (ResidentUtil.isProessRunning(context, messageServiceName)) {
//            Intent i = new Intent(context, MessageService.class);
//            context.stopService(i);
        }
    }
}
