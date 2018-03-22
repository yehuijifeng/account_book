package com.lh.accountbook.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lh.accountbook.R;
import com.lh.accountbook.adapter.AccountEditAdapter;
import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.bean.account.AccountInfoBean;
import com.lh.accountbook.databinding.ActivityEditAccountBinding;
import com.lh.accountbook.test;

import java.util.List;

/**
 * Created by LuHao on 2018/3/22.
 * 添加账单
 */

public class AccountEditActivity extends BaseActivity {
    private ActivityEditAccountBinding activityEditAccountBinding;
    private AccountInfoBean accountInfoBean;
    private List<AccountEditTypeBean> typeBeanList;

    @Override
    protected void setDataBinding() {
        activityEditAccountBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_account);
    }

    @Override
    protected void initView() {
        activityEditAccountBinding.toolbar.setTitle(R.string.account_edit_title);
        setSupportActionBar(activityEditAccountBinding.toolbar);
    }

    @Override
    protected void initData() {
        //初始化常用账单类型
        AccountEditAdapter accountEditAdapter = new AccountEditAdapter(test.getEditAccountDataTwo(), new OnItemClickListener());
        activityEditAccountBinding.rvIcon.setAdapter(accountEditAdapter);
        activityEditAccountBinding.rvIcon.setLayoutManager(new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false));
        accountEditAdapter.notifyDataSetChanged();

        //初始化账单
        typeBeanList = test.getEditAccountData();
        //accountInfoBean = getBundle().getParcelable(BundleConstant.AccountInfoBean);
        if (accountInfoBean == null)
            accountInfoBean = new AccountInfoBean(typeBeanList.get(0).getTypeIcon(), typeBeanList.get(0).getTypeName());
        activityEditAccountBinding.setAccountinfo(accountInfoBean);
        activityEditAccountBinding.imgIcon.setImageResource(accountInfoBean.getAccountTypeIcon());
        activityEditAccountBinding.textType.setText(accountInfoBean.getAccountType());
    }

    private class OnItemClickListener implements AccountEditAdapter.OnItemClickListener {

        @Override
        public void onItemClick(int position, View view) {
            if (position == test.getEditAccountDataTwo().size() - 1) {
                startActivity(HomeActivity.class);
            } else {
                activityEditAccountBinding.imgIcon.setImageResource(test.getEditAccountDataTwo().get(position).getTypeIcon());
                activityEditAccountBinding.textType.setText(test.getEditAccountDataTwo().get(position).getTypeName());
            }
        }
    }
}
