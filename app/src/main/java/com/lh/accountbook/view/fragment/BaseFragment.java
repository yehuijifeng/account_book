package com.lh.accountbook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LuHao on 2018/3/20.
 * fragment基类
 */

public abstract class BaseFragment extends Fragment {

    protected abstract int setContentView();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected boolean isLoadComplete = true;//fragment是否加载完成

    /**
     * 创建视图,传入根view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(setContentView(), container, false);
    }

    /**
     * 视图创建,当前视图被调用的时候，activity才会被传入进来
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        loadData();
    }

    private void loadData() {
        if (!isLoadComplete) return;
        initData();
        isLoadComplete = false;
    }

    /**
     * 选中状态
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
