package com.lh.accountbook.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lh.accountbook.BuildConfig;
import com.lh.accountbook.service.apprun.MessageServiceUtil;
import com.lh.accountbook.service.apprun.ResidentUtil;

/**
 * Created by LuHao on 2017/6/20.
 */

public class ResidentTwoService extends Service {

    private MessageServiceUtil messageServiceUtil;

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) startS1;
    }

    private final String Process_Name = BuildConfig.APPLICATION_ID+":AccountBookServiceTwo";


    /**
     * 启动Service1
     */
    private StrongService startS1 = new StrongService() {

        @Override
        public void stopService() {
            Intent i = new Intent(getBaseContext(), ResidentOneService.class);
            getBaseContext().stopService(i);
        }

        @Override
        public void startService() {
            Intent i = new Intent(getBaseContext(), ResidentOneService.class);
            getBaseContext().startService(i);

        }
    };

    @Override
    public void onTrimMemory(int level) {
        String content = "" + level;
        switch (level) {
            case TRIM_MEMORY_COMPLETE://80
                content = "内存不足，并且该进程在后台进程列表最后一个，马上就要被清理";
                break;
            case TRIM_MEMORY_MODERATE://60
                content = "内存不足，并且该进程在后台进程列表的中部。";
                break;
            case TRIM_MEMORY_BACKGROUND://40
                content = "内存不足，并且该进程是后台进程。";
                break;
            case TRIM_MEMORY_UI_HIDDEN://20
                content = "内存不足，并且该进程的UI已经不可见了。";
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL://15
                content = "内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存";
                break;
            case TRIM_MEMORY_RUNNING_LOW://10
                content = "内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存";
                break;
            case TRIM_MEMORY_RUNNING_MODERATE://5
                content = "内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存";
                break;
        }
        //Toast.makeText(getBaseContext(), "Service2 :" + content, Toast.LENGTH_SHORT).show();
        if (level == TRIM_MEMORY_MODERATE)
            keepService1();
    }

    @Override
    public void onCreate() {
        //Toast.makeText(ResidentTwoService.this, "Service2 onCreate...", Toast.LENGTH_SHORT).show();
        if (messageServiceUtil == null)
            messageServiceUtil = new MessageServiceUtil();
        messageServiceUtil.startMessageService(this);
        keepService1();
    }

    /**
     * 判断Service1是否还在运行，如果不是则启动Service1
     */
    private void keepService1() {
        boolean isRun = ResidentUtil.isProessRunning(ResidentTwoService.this, Process_Name);
        if (!isRun) {
            //Toast.makeText(getBaseContext(), "重新启动 Service1", Toast.LENGTH_SHORT).show();
            startS1.startService();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }


}
