package com.lh.accountbook.view.activity.acount;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.lh.accountbook.R;
import com.lh.accountbook.adapter.AccountTypeListAdapter;
import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.constant.BundleConstant;
import com.lh.accountbook.databinding.ActivityAccountTypeListBinding;
import com.lh.accountbook.test;
import com.lh.accountbook.view.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuHao on 2018/3/23.
 * 账单类型列表
 */

public class AccountTypeListActivity extends BaseActivity {
    private ActivityAccountTypeListBinding activityAccountTypeListBinding;
    private final int add_account_type_code = 2018;
    private List<AccountEditTypeBean> data = new ArrayList<>();
    private AccountTypeListAdapter accountTypeAdapter;
    private int refreshType;//是否需要重新查询。0，不需要；1，修改了数据；2，添加了数据

    @Override
    protected void setDataBinding() {
        activityAccountTypeListBinding = DataBindingUtil.setContentView(this, R.layout.activity_account_type_list);
    }

    @Override
    protected void initView() {
        initToolbar(activityAccountTypeListBinding.toolbar);
    }

    @Override
    protected void initData() {
        data = test.getEditAccountData();
        accountTypeAdapter = new AccountTypeListAdapter(data, new AccountTypeListAdapter.OnAccountTypeItemClick() {
            @Override
            public void onItemClick(int position) {
                //修改
                Bundle bundle = new Bundle();
                bundle.putInt(BundleConstant.AccountEditTypePosition, position);
                bundle.putParcelable(BundleConstant.AccountEditTypeBean, data.get(position));
                startActivityForResult(AddAccountTypeActivity.class, bundle, add_account_type_code);
            }
        });
        activityAccountTypeListBinding.rvType.setAdapter(accountTypeAdapter);
        activityAccountTypeListBinding.rvType.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        accountTypeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void finishActivity() {
        backAccount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_type_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            startActivityForResult(AddAccountTypeActivity.class, add_account_type_code);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == add_account_type_code) {
                if (data != null) {
                    AccountEditTypeBean accountEditTypeBean = data.getParcelableExtra(BundleConstant.AccountEditTypeBean);
                    if (accountEditTypeBean != null) {
                        //添加数据库
                        int position = data.getIntExtra(BundleConstant.AccountEditTypePosition, -1);
                        if (position >= 0) {
                            this.data.set(position, accountEditTypeBean);
                            refreshType = 1;
                        } else {
                            this.data.add(0, accountEditTypeBean);
                            refreshType = 2;
                        }
                        accountTypeAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    private void backAccount() {
        Intent intent = new Intent();
        intent.putExtra(BundleConstant.refreshType, refreshType);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backAccount();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
