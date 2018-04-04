package com.lh.accountbook.view.activity.index;

import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.lh.accountbook.R;
import com.lh.accountbook.appliaction.ABAppliaction;
import com.lh.accountbook.appliaction.CrashHandler;
import com.lh.accountbook.appliaction.PermissionsChecker;
import com.lh.accountbook.bean.UserInfoBean;
import com.lh.accountbook.constant.AppConstant;
import com.lh.accountbook.db.UserInfoDao;
import com.lh.accountbook.utils.DateUtils;
import com.lh.accountbook.utils.LogUtils;
import com.lh.accountbook.utils.SharedPreferencesUtils;
import com.lh.accountbook.view.activity.base.BaseActivity;

/**
 * Created by LuHao on 2018/3/21.
 * loading页
 */

public class LoadingActivity extends BaseActivity {

    private final int PERMISSIONS_BACK = 2018;

    @Override
    protected void setDataBinding() {
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected boolean isFullWindow() {
        return true;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        DateUtils.getNetWorkTime();
        checkAuthority();
    }


    /**
     * 检查权限
     */
    private void checkAuthority() {
        // 所需的全部权限
        String[] PERMISSIONS = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,//写sd
        };
        PermissionsChecker mPermissionsChecker = new PermissionsChecker();
        //判断权限是否够，6.0以上需要动态权限
        String[] shenqingPERMISSIONS = mPermissionsChecker.lacksPermissions(this, PERMISSIONS);
        if (Build.VERSION.SDK_INT >= 23 && shenqingPERMISSIONS != null) {
            ActivityCompat.requestPermissions(this, shenqingPERMISSIONS, PERMISSIONS_BACK);
        } else {
            initAPP();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(HomeActivity.class);
                    finish();
                }
            }, 500);
        }
    }

    /**
     * 初始化app
     */
    private void initAPP() {
        //全局捕获异常的代理类
        CrashHandler.getInstance().init(getApplicationContext());
        UserInfoDao userInfoDao = new UserInfoDao();
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils();
        int isOneStartApp = sharedPreferencesUtils.getInt(AppConstant.IS_ONE_START_APP);
        if (isOneStartApp == 0) {//第一次进入app，初始化
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUserId(1000);
            userInfoBean.setUserName("记账本用户——1");
            userInfoBean.setUserRegisterTime(DateUtils.getSystemTime());
            userInfoDao.dao.deleteDataById(ABAppliaction.userId);
//            userInfoDao.insertUserInfo(userInfoBean);
            LogUtils.i("用户表创建：" + (userInfoDao.dao.insertData(userInfoBean) == 1 ? "成功" : "失败"));
        } else {
            ABAppliaction.INSTANCE.setUserInfo((UserInfoBean) userInfoDao.dao.queryByUserId(ABAppliaction.userId));
        }
    }

    /**
     * 用户选择权限后的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_BACK) {
            checkAuthority();//再次检查权限是否足够
        }
    }
}
