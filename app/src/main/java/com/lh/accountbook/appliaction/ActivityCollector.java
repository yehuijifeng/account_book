package com.lh.accountbook.appliaction;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yehuijifeng
 * on 2015/10/27.
 * activity的收藏夹
 */
public class ActivityCollector {

    /**
     * 保存整个app所有活动的activity，用于关闭整个程序所用
     */
    public static List<Activity> activitiys = new ArrayList<>();


    /**
     * 添加activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activitiys.add(activity);
    }

    /**
     * 从集合中移除activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null)
            activitiys.remove(activity);
    }

    /**
     * 当前app是否在栈顶
     *
     * @param activity
     */
    public static boolean checkActivityTop(Activity activity) {
        if (activitiys.size() == 0) return false;
        return activitiys.get(activitiys.size() - 1) == activity;
    }

    /**
     * 该操作用于清空当前app栈中所有activity，用于退出程序所用
     */
    public static void finishAll() {
        for (Activity activity : activitiys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 该操作用于清空当前app栈中所有activity，保留目标activity
     */
    public static void finishAll(Activity activity1) {
        for (Activity activity : activitiys) {
            if (!activity.isFinishing() && activity != activity1) {
                activity.finish();
            }
        }
    }

    /**
     * 该操作用于清空当前app栈中所有activity，保留目标activity
     */
    public static void finishAll(Class cla) {
        for (Activity activity : activitiys) {
            if (!activity.getComponentName().toString().contains(cla.getName())) {
                activity.finish();
            }
        }
    }

    public static void finishByActivity(Class cla) {
        for (Activity activity : activitiys) {
            if (activity.getComponentName().toString().contains(cla.getName())) {
                activity.finish();
                return;
            }
        }
    }

    public static boolean isContain(Activity activity) {
        return activitiys.contains(activity);
    }

    public static boolean isContain(Class cla) {
        for (Activity activity : activitiys) {
            if (activity.getComponentName().toString().contains(cla.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTop(Activity activity) {
        if (activitiys == null || activitiys.size() == 0) return false;
        return activitiys.get(activitiys.size() - 1).equals(activity);
    }

    /**
     * 跳转到栈内
     *
     * @param cla
     */
    public static void startBackActivity(Class cla) {
        for (int i = activitiys.size() - 1; i > 0; i--) {
            if (activitiys.get(i).getComponentName().toString().contains(cla.getName())) {
                return;
            }
            activitiys.get(i).finish();
        }
    }

    /**
     * 杀死该应用进程
     */
    public static void killProcess() {
        finishAll();
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
