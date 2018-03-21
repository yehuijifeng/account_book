package com.lh.accountbook.view.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by LuHao on 2018/3/20.
 * 基础activity
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void setDataBinding();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 是否全屏
     *
     * @return
     */
    protected boolean isFullWindow() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (isFullWindow()) {
            //去除标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //去除状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setDataBinding();
        initActivity();
        initView();
        initData();
    }

    private void initActivity() {

    }

    protected void startActivity(Class cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }

    protected void startActivity(Class cla, Bundle bundle) {
        Intent intent = new Intent(this, cla);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    protected Bundle getBundle() {
        if (getIntent() == null) return null;
        return getIntent().getExtras();
    }
}
