package com.lh.accountbook.view.activity.acount;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.lh.accountbook.R;
import com.lh.accountbook.adapter.AccountEditAdapter;
import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.bean.account.AccountInfoBean;
import com.lh.accountbook.constant.BundleConstant;
import com.lh.accountbook.databinding.ActivityEditAccountBinding;
import com.lh.accountbook.test;
import com.lh.accountbook.utils.DateUtils;
import com.lh.accountbook.utils.ToastUtils;
import com.lh.accountbook.view.activity.base.BaseActivity;

import java.util.List;

/**
 * Created by LuHao on 2018/3/22.
 * 添加账单
 */

public class AccountEditActivity extends BaseActivity {
    private ActivityEditAccountBinding activityEditAccountBinding;
    private AccountInfoBean accountInfoBean;
    private List<AccountEditTypeBean> typeBeanList;
    private final int type_list_code = 2019;
    private AccountEditAdapter accountEditAdapter;

    @Override
    protected void setDataBinding() {
        activityEditAccountBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_account);
    }

    @Override
    protected void initView() {
        initToolbar(activityEditAccountBinding.toolbar);
    }

    @Override
    protected void initData() {
        //初始化账单
        refreshAccountType();
        //初始化常用账单类型
        accountEditAdapter = new AccountEditAdapter(typeBeanList, new OnItemClickListener());
        activityEditAccountBinding.rvIcon.setAdapter(accountEditAdapter);
        activityEditAccountBinding.rvIcon.setLayoutManager(new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false));
        accountEditAdapter.notifyDataSetChanged();
        if (accountInfoBean == null)
            accountInfoBean = new AccountInfoBean(typeBeanList.get(0).getTypeIcon(), typeBeanList.get(0).getTypeName());
        activityEditAccountBinding.setAccountinfo(accountInfoBean);
        activityEditAccountBinding.imgIcon.setImageResource(accountInfoBean.getAccountTypeIcon());
        activityEditAccountBinding.textType.setText(accountInfoBean.getAccountType());
        watchSearch();
    }

    private void refreshAccountType() {
        typeBeanList = test.getUserAccountTypeData();
    }

    /**
     * 点击账单类型
     */
    private class OnItemClickListener implements AccountEditAdapter.OnItemClickListener {

        @Override
        public void onItemClick(int position, View view) {
            if (position == typeBeanList.size() - 1) {
                startActivityForResult(AccountTypeListActivity.class, type_list_code);
            } else {
                activityEditAccountBinding.imgIcon.setImageResource(typeBeanList.get(position).getTypeIcon());
                activityEditAccountBinding.textType.setText(typeBeanList.get(position).getTypeName());
                accountInfoBean.setAccountType(typeBeanList.get(position).getTypeName());
                accountInfoBean.setAccountTypeIcon(typeBeanList.get(position).getTypeIcon());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == type_list_code) {
                if (data != null) {
                    int editType = data.getIntExtra(BundleConstant.refreshType, 0);
                    if (editType != 0) {
                        refreshAccountType();
                        accountEditAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    /**
     * 菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_right_btn, menu);
        return true;//true,有三个点；false，关闭菜单
    }

    /**
     * 菜单的每个点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.confirm) {
            backAddAccount();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @方法说明:监控软键盘的的搜索按钮
     * @方法名称:watchSearch
     * @返回值:void
     */
    public void watchSearch() {
        activityEditAccountBinding.editMoney.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 先隐藏键盘
                    ((InputMethodManager) activityEditAccountBinding.editMoney.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(AccountEditActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    backAddAccount();
                    return true;
                }
                return false;
            }
        });
    }

    private void backAddAccount() {
        if (TextUtils.isEmpty(activityEditAccountBinding.editMoney.getText())) {
            ToastUtils.INSTANCE.showErrorToast(this, "请输入账单金额");
        } else {
            double money = Double.parseDouble(activityEditAccountBinding.editMoney.getText().toString());
            if (money == 0) {
                ToastUtils.INSTANCE.showErrorToast(this, "账单金额不能为0");
            } else {
                accountInfoBean.setMoney(money);
                accountInfoBean.setOut(money > 0);
                accountInfoBean.setCreateTime(DateUtils.getSystemTime());
                if (!TextUtils.isEmpty(activityEditAccountBinding.editTips.getText()))
                    accountInfoBean.setTips(activityEditAccountBinding.editTips.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(BundleConstant.AccountInfoBean, accountInfoBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
