package com.lh.accountbook.db;

import com.lh.accountbook.bean.UserInfoBean;
import com.lh.accountbook.db.base.BaseDBDao;

/**
 * Created by LuHao on 2018/4/4.
 */

public class UserInfoDao {
    public BaseDBDao<UserInfoBean> dao;

    public UserInfoDao() {
        dao = new BaseDBDao<>(UserInfoBean.class);
    }



}
