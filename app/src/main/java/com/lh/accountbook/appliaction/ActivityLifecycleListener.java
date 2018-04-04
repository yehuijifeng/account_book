package com.lh.accountbook.appliaction;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.lh.accountbook.constant.AppConstant;
import com.lh.accountbook.service.ResidentOneService;


/**
 * Created by LuHao on 2017/4/17.
 * 监听app是否在后台
 */

public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {

    private int refCount = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        refCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        AppConstant.IS_APP_TOP = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        refCount--;
        if (refCount == 0) {
            //说明不在栈顶了
            AppConstant.IS_APP_TOP = false;
            Intent intent = new Intent(activity, ResidentOneService.class);
            activity.startService(intent);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
