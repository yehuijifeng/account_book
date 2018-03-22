package com.lh.accountbook;

import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.bean.account.AccountInfoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LuHao on 2018/3/22.
 */

public class test {

    public static final int[] icons = new int[]{
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
            R.drawable.icon_add_48};

    public static List getHomeData() {
        Random random = new Random();
        List<AccountInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            AccountInfoBean accountInfoBean = new AccountInfoBean();
            accountInfoBean.setTips(i % 2 == 0 ? null : "测试备注数据" + i);
            accountInfoBean.setAccountType("记账类型" + i);
            accountInfoBean.setAccountTypeIcon(test.icons[random.nextInt(47)]);
            accountInfoBean.setCreateTime(System.currentTimeMillis());
            accountInfoBean.setMoney((i * 0.34));
            accountInfoBean.setOut(i % 2 == 0);
            list.add(accountInfoBean);
        }
        return list;
    }

    public static List<AccountEditTypeBean> getEditAccountData() {
        List<AccountEditTypeBean> list = new ArrayList<>();
        for (int i = 0; i < 47; i++) {
            AccountEditTypeBean accountInfoBean = new AccountEditTypeBean();
            accountInfoBean.setTypeIcon(icons[i]);
            accountInfoBean.setTypeName("类型" + i);
            list.add(accountInfoBean);
        }
        for (int i = 0; i < 47; i++) {
            AccountEditTypeBean accountInfoBean = new AccountEditTypeBean();
            accountInfoBean.setTypeIcon(icons[i]);
            accountInfoBean.setTypeName("类型" + i);
            list.add(accountInfoBean);
        }
        return list;
    }

    public static List<AccountEditTypeBean> getEditAccountDataTwo() {
        List<AccountEditTypeBean> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            AccountEditTypeBean accountInfoBean = new AccountEditTypeBean();
            accountInfoBean.setTypeIcon(icons[i]);
            accountInfoBean.setTypeName("类型" + i);
            list.add(accountInfoBean);
        }
        AccountEditTypeBean accountInfoBean = new AccountEditTypeBean();
        accountInfoBean.setTypeIcon(R.drawable.icon_add_edit);
        accountInfoBean.setTypeName("编辑");
        list.add(accountInfoBean);
        return list;
    }

}
