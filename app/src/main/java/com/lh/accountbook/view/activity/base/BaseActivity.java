package com.lh.accountbook.view.activity.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lh.accountbook.appliaction.ActivityCollector;

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
        ActivityCollector.addActivity(this);
    }

    /**
     * 如果toolbar有返回按钮，则增加返回点击事件
     *
     * @param toolbar
     */
    protected void initToolbar(Toolbar toolbar) {
        initToolbar(toolbar, null);
    }

    protected void initToolbar(Toolbar toolbar, CharSequence titleStr) {
        if (!TextUtils.isEmpty(titleStr)) {
            toolbar.setTitle(titleStr);
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    protected void finishActivity(){
        finish();
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

    protected void startActivityForResult(Class cla, int code) {
        Intent intent = new Intent(this, cla);
        startActivityForResult(intent, code);
    }

    protected void startActivityForResult(Class cla, Bundle bundle, int code) {
        Intent intent = new Intent(this, cla);
        if (bundle != null) intent.putExtras(bundle);
        startActivityForResult(intent, code);
    }

    protected Bundle getBundle() {
        if (getIntent() == null) return null;
        return getIntent().getExtras();
    }

    /**
     * @param key          对应bundle中的标识
     * @param defaultValue 默认值，娶不到bundle中的值的时候返回该默认值
     */
    public int getInt(String key, int defaultValue) {
        if (getBundle() != null)
            return getBundle().getInt(key, defaultValue);
        else
            return defaultValue;
    }

    /**
     * 下同，，，，bundle的用法
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public String getString(String key, String defaultValue) {
        if (getBundle() != null)
            return getBundle().getString(key, defaultValue);
        else
            return defaultValue;
    }

    /**
     * 获取上一个Activity传过来的布尔值
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getBundle() != null && getBundle().getBoolean(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的double值
     */
    public double getDouble(String key, double defaultValue) {
        if (getBundle() != null)
            return getBundle().getDouble(key, defaultValue);
        else
            return defaultValue;
    }

    /**
     * 获取上一个Activity传过来的float值
     */
    public float getFloat(String key, float defaultValue) {
        if (getBundle() != null)
            return getBundle().getFloat(key, defaultValue);
        else
            return defaultValue;
    }

    /**
     * 获取上一个Activity传过来的实现了Parcelable接口的对象
     */
    public Parcelable getParcelable(String key) {
        if (getBundle() != null)
            return getBundle().getParcelable(key);
        else
            return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
