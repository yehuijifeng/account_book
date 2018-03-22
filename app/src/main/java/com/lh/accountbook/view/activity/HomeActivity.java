package com.lh.accountbook.view.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lh.accountbook.R;
import com.lh.accountbook.adapter.AccountAdapter;
import com.lh.accountbook.bean.UserInfoBean;
import com.lh.accountbook.bean.account.AccountBudgetBean;
import com.lh.accountbook.bean.account.AccountInfoBean;
import com.lh.accountbook.databinding.ActivityHomeBinding;
import com.lh.accountbook.utils.DateUtils;
import com.lh.accountbook.utils.ShareUtils;
import com.lh.accountbook.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private ActivityHomeBinding activityHomeBinding;
    private AccountAdapter accountAdapter;

    @Override
    protected void setDataBinding() {
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }

    @Override
    protected void initView() {
        //toolbar 初始化
        activityHomeBinding.appBarHome.toolbar.setTitle(R.string.app_name);
        activityHomeBinding.appBarHome.toolbar.setOnClickListener(this);
        setSupportActionBar(activityHomeBinding.appBarHome.toolbar);
        //初始化抽屉
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                activityHomeBinding.drawerLayout,
                activityHomeBinding.appBarHome.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        activityHomeBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //抽屉点击事件
        activityHomeBinding.navView.setNavigationItemSelectedListener(this);
        //抽屉图标涂色
        activityHomeBinding.navView.setItemIconTintList(null);
        //抽屉头部布局
        View headerView = activityHomeBinding.navView.getHeaderView(0);
        ImageView img_user_icon = (ImageView) headerView.findViewById(R.id.img_user_icon);
        img_user_icon.setImageResource(R.drawable.ic_user_icon);
        TextView text_user_name = (TextView) headerView.findViewById(R.id.text_user_name);
        text_user_name.setText("记账本-用户1");


    }

    @Override
    protected void initData() {
        activityHomeBinding.setUserinfo(new UserInfoBean());
        activityHomeBinding.appBarHome.setOnAccountClickListener(new OnAccountClickListener());
        activityHomeBinding.appBarHome.rvAccount.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        accountAdapter = new AccountAdapter(getAccountBudgetBean(), getData());
        activityHomeBinding.appBarHome.rvAccount.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
        addScrollViewLisenter();
    }

    private void addScrollViewLisenter() {
        activityHomeBinding.appBarHome.rvAccount.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > 10;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        activityHomeBinding.appBarHome.fabAddAccount.hide();
                    } else {
                        activityHomeBinding.appBarHome.fabAddAccount.show();
                    }
                }
            }
        });
    }

    /**
     * 添加账单操作
     */
    public class OnAccountClickListener {

        public void addAccount(View view) {
            Snackbar.make(view, "当前时间：" + DateUtils.format(DateUtils.FORMAT_LONG_CN), Snackbar.LENGTH_LONG)
                    .setAction("添加", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:

                break;
        }
    }


    /**
     * 三个点的菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;//true,有三个点；false，关闭菜单
    }

    /**
     * 三个点菜单的每个点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_setting:
                break;
            case R.id.item_share:
                ShareUtils.shareApp(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 抽屉item点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_money:
                ToastUtils.INSTANCE.showSuccessToast(this, "资产");
                break;
            case R.id.item_table:
                ToastUtils.INSTANCE.showSuccessToast(this, "报表");
                break;
            case R.id.item_trend:
                ToastUtils.INSTANCE.showSuccessToast(this, "趋势");
                break;
            case R.id.item_excel:
                ToastUtils.INSTANCE.showSuccessToast(this, "导出excel");
                break;
            case R.id.item_guanyu:
                ToastUtils.INSTANCE.showSuccessToast(this, "关于");
                break;
            case R.id.item_share:
                ToastUtils.INSTANCE.showSuccessToast(this, "分享");
                break;
            case R.id.item_setting:
                ToastUtils.INSTANCE.showSuccessToast(this, "设置");
                break;
        }
        activityHomeBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 监听返回按键
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private AccountBudgetBean getAccountBudgetBean() {
        return new AccountBudgetBean(3000, 15, 0, 1000, 3, 1);
    }

    private List getData() {
        Random random = new Random();
        List<AccountInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            AccountInfoBean accountInfoBean = new AccountInfoBean();
            accountInfoBean.setTips(i % 2 == 0 ? null : "测试备注数据" + i);
            accountInfoBean.setAccountType("记账类型" + i);
            accountInfoBean.setAccountTypeIcon(icons[random.nextInt(47)]);
            accountInfoBean.setCreateTime(System.currentTimeMillis());
            accountInfoBean.setMoney((i * 0.34));
            accountInfoBean.setOut(i % 2 == 0);
            list.add(accountInfoBean);
        }
        return list;
    }

    private final int[] icons = new int[]{
            R.drawable.icon_add_1,
            R.drawable.icon_add_2,
            R.drawable.icon_add_3,
            R.drawable.icon_add_4,
            R.drawable.icon_add_5,
            R.drawable.icon_add_6,
            R.drawable.icon_add_7,
            R.drawable.icon_add_8,
            R.drawable.icon_add_9,
            R.drawable.icon_add_10,
            R.drawable.icon_add_11,
            R.drawable.icon_add_12,
            R.drawable.icon_add_13,
            R.drawable.icon_add_14,
            R.drawable.icon_add_15,
            R.drawable.icon_add_16,
            R.drawable.icon_add_17,
            R.drawable.icon_add_18,
            R.drawable.icon_add_19,
            R.drawable.icon_add_20,
            R.drawable.icon_add_21,
            R.drawable.icon_add_22,
            R.drawable.icon_add_23,
            R.drawable.icon_add_24,
            R.drawable.icon_add_25,
            R.drawable.icon_add_26,
            R.drawable.icon_add_27,
            R.drawable.icon_add_28,
            R.drawable.icon_add_29,
            R.drawable.icon_add_30,
            R.drawable.icon_add_31,
            R.drawable.icon_add_32,
            R.drawable.icon_add_33,
            R.drawable.icon_add_34,
            R.drawable.icon_add_35,
            R.drawable.icon_add_36,
            R.drawable.icon_add_37,
            R.drawable.icon_add_38,
            R.drawable.icon_add_39,
            R.drawable.icon_add_40,
            R.drawable.icon_add_41,
            R.drawable.icon_add_42,
            R.drawable.icon_add_43,
            R.drawable.icon_add_44,
            R.drawable.icon_add_45,
            R.drawable.icon_add_46,
            R.drawable.icon_add_47,
            R.drawable.icon_add_48,};
}
