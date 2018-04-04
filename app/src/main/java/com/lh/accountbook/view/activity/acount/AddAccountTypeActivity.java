package com.lh.accountbook.view.activity.acount;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lh.accountbook.R;
import com.lh.accountbook.adapter.AccountEditAdapter;
import com.lh.accountbook.adapter.AddAccountTypeAdapter;
import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.constant.BundleConstant;
import com.lh.accountbook.databinding.ActivityAddAccountTypeBinding;
import com.lh.accountbook.test;
import com.lh.accountbook.utils.ToastUtils;
import com.lh.accountbook.view.activity.base.BaseActivity;

import java.util.List;

/**
 * Created by LuHao on 2018/4/3.
 */

public class AddAccountTypeActivity extends BaseActivity {

    private ActivityAddAccountTypeBinding activityAddAccountTypeBinding;
    private AccountEditTypeBean cacheAccountEditTypeBean;
    private AddAccountTypeAdapter addAccountTypeAdapter;
    private List<AccountEditTypeBean> data;

    @Override
    protected void setDataBinding() {
        activityAddAccountTypeBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_account_type);
    }


    @Override
    protected void initView() {
        initToolbar(activityAddAccountTypeBinding.toolbar);
        data = test.getEditAccountData();
        cacheAccountEditTypeBean = data.get(0);
        addAccountTypeAdapter = new AddAccountTypeAdapter(data, new AccountEditAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                cacheAccountEditTypeBean = data.get(position);
                activityAddAccountTypeBinding.imgIcon.setImageResource(cacheAccountEditTypeBean.getTypeIcon());
            }
        });
        activityAddAccountTypeBinding.rvIcon.setAdapter(addAccountTypeAdapter);
        activityAddAccountTypeBinding.rvIcon.setLayoutManager(new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false));
        addAccountTypeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        if (getBundle() != null) {
            AccountEditTypeBean accountEditTypeBean = getBundle().getParcelable(BundleConstant.AccountEditTypeBean);
            if (accountEditTypeBean != null) {
                activityAddAccountTypeBinding.textName.setText(accountEditTypeBean.getTypeName());
                activityAddAccountTypeBinding.imgIcon.setImageResource(accountEditTypeBean.getTypeIcon());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_type_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            saveAccountData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAccountData() {
        if (TextUtils.isEmpty(activityAddAccountTypeBinding.textName.getText())) {
            ToastUtils.INSTANCE.showErrorToast(this, "请输入账单类型名称");
        } else {
            AccountEditTypeBean accountEditTypeBean = new AccountEditTypeBean();
            accountEditTypeBean.setTypeName(activityAddAccountTypeBinding.textName.getText().toString());
            accountEditTypeBean.setTypeIcon(cacheAccountEditTypeBean.getTypeIcon());
            Intent intent = new Intent();
            intent.putExtra(BundleConstant.AccountEditTypePosition, getInt(BundleConstant.AccountEditTypePosition, -1));
            intent.putExtra(BundleConstant.AccountEditTypeBean, accountEditTypeBean);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
