package com.lh.accountbook.bean.account;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * 预算
 */
@DatabaseTable
public class AccountBudgetBean {
    @DatabaseField(id = true)
    private int budgetId=1;
    @DatabaseField
    private double money;//每个月预算
    @DatabaseField
    private int closeTime;//结算日。1-28号。默认0，0为月末
    private int userId;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
}
