package com.lh.accountbook.appliaction;


import android.support.multidex.MultiDexApplication;

import com.lh.accountbook.bean.UserInfoBean;
import com.lh.accountbook.db.UserInfoDao;
import com.lh.accountbook.utils.DateUtils;

/**
 * Created by LuHao on 2018/3/19.
 * mvvm开发模式
 */

public class ABAppliaction extends MultiDexApplication {

    public static ABAppliaction INSTANCE;
    private static UserInfoBean userInfoBean;
    public static final int userId = 10000;

    public UserInfoBean getUserInfo() {
        if (userInfoBean == null) {
            UserInfoDao userInfoDao = new UserInfoDao();
            userInfoBean = (UserInfoBean) userInfoDao.dao.queryByUserId(userId);
            if (userInfoBean == null) {
                userInfoBean = new UserInfoBean();
                userInfoBean.setUserId(userId);
                userInfoBean.setUserName("记账本用户-1");
                userInfoBean.setUserRegisterTime(DateUtils.getSystemTime());
                userInfoDao.dao.insertData(userInfoBean);
            }
        }
        return userInfoBean;
    }

    public void setUserInfo(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (INSTANCE == null)
            synchronized (this) {
                if (INSTANCE == null) {
                    INSTANCE = this;
                    registerActivityLifecycleCallbacks(new ActivityLifecycleListener());

                }
            }
    }


}
