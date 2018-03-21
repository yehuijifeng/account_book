package com.lh.accountbook.view.activity;

import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.lh.accountbook.R;
import com.lh.accountbook.appliaction.CrashHandler;
import com.lh.accountbook.appliaction.PermissionsChecker;

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
        checkAuthority();
    }

    @Override
    protected void initData() {
            //aaa
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
            }, 1 * 1000);
        }
    }

    /**
     * 初始化app
     */
    private void initAPP() {
        //全局捕获异常的代理类
        CrashHandler.getInstance().init(getApplicationContext());
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
