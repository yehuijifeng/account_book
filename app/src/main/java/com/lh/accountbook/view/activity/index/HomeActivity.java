package com.lh.accountbook.view.activity.index;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lh.accountbook.R;
import com.lh.accountbook.adapter.AccountAdapter;
import com.lh.accountbook.appliaction.ABAppliaction;
import com.lh.accountbook.appliaction.ActivityCollector;
import com.lh.accountbook.bean.UserInfoBean;
import com.lh.accountbook.bean.account.AccountBudgetBean;
import com.lh.accountbook.bean.account.AccountInfoBean;
import com.lh.accountbook.constant.BundleConstant;
import com.lh.accountbook.databinding.ActivityHomeBinding;
import com.lh.accountbook.service.ResidentOneService;
import com.lh.accountbook.test;
import com.lh.accountbook.utils.DateUtils;
import com.lh.accountbook.utils.ShareUtils;
import com.lh.accountbook.utils.ToastUtils;
import com.lh.accountbook.view.activity.acount.AccountEditActivity;
import com.lh.accountbook.view.activity.base.BaseActivity;

import java.util.List;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private ActivityHomeBinding activityHomeBinding;
    private AccountAdapter accountAdapter;
    private final int add_account_code = 2018;
    private List list;

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
        text_user_name.setText(ABAppliaction.INSTANCE.getUserInfo().getUserName());
    }

    @Override
    protected void initData() {
        list = test.getHomeData();
        activityHomeBinding.setUserinfo(new UserInfoBean());
        activityHomeBinding.appBarHome.setOnAccountClickListener(new OnAccountClickListener());
        activityHomeBinding.appBarHome.rvAccount.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        accountAdapter = new AccountAdapter(list);
        activityHomeBinding.appBarHome.rvAccount.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
        addScrollViewLisenter();
        getAccountBudgetBean();
    }

    /**
     * recyclerviwe的滑动监听
     */
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
            startActivityForResult(AccountEditActivity.class, add_account_code);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == add_account_code) {
                if (data != null) {
                    AccountInfoBean accountInfoBean = data.getParcelableExtra(BundleConstant.AccountInfoBean);
                    if (accountInfoBean != null) {
                        list.add(0, accountInfoBean);
                        accountAdapter.notifyDataSetChanged();
                    }
                }
            }
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

    private long exitTime;

    /**
     * 监听返回按键
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((DateUtils.getSystemTime() - exitTime) > 2000) {
                ToastUtils.INSTANCE.showShortToast(this, "再按一次退出应用");
                exitTime = DateUtils.getSystemTime();
            } else {
                ToastUtils.INSTANCE.cancelToast();
                Intent intent = new Intent(this, ResidentOneService.class);
                startService(intent);
                ActivityCollector.finishAll();
            }
            super.onBackPressed();
        }
    }

    private void getAccountBudgetBean() {
        final AccountBudgetBean accountBudgetBean = new AccountBudgetBean(3000, 15, 0, 1000, 3, 1);
        activityHomeBinding.appBarHome.accountHander.setBudgetinfo(accountBudgetBean);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int fl_height = activityHomeBinding.appBarHome.accountHander.flRemaining.getHeight();
                ImageView iv = activityHomeBinding.appBarHome.accountHander.imgRemainingMoney;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) iv.getLayoutParams();
                double bottomMargin = fl_height *accountBudgetBean.getMonthRemovemoney() / accountBudgetBean.getMoney();
                layoutParams.height = (int) bottomMargin;
                iv.setLayoutParams(layoutParams);
            }
        }, 200);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((DateUtils.getSystemTime() - exitTime) > 2000) {
                ToastUtils.INSTANCE.showShortToast(this, "再按一次退出应用");
                exitTime = DateUtils.getSystemTime();
            } else {
                ToastUtils.INSTANCE.cancelToast();
                ActivityCollector.finishAll();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}


