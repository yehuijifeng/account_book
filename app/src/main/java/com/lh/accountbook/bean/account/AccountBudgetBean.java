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
    private int budgetId = 1;
    @DatabaseField
    private double money;//每个月预算
    @DatabaseField
    private int closeTime;//结算日。1-28号。默认0，0为月末
    private double monthAddmoney;//月收入
    private double monthRemovemoney;//月支出
    private int recordMonth;//当前预算记录月份，若月份变更，则收入计算清零
    private int userId;
    public AccountBudgetBean(){}
    public AccountBudgetBean(double money, int closeTime, double monthAddmoney, double monthRemovemoney, int recordMonth, int userId) {
        this.money = money;
        this.closeTime = closeTime;
        this.monthAddmoney = monthAddmoney;
        this.monthRemovemoney = monthRemovemoney;
        this.recordMonth = recordMonth;
        this.userId = userId;
    }

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

    public double getMonthAddmoney() {
        return monthAddmoney;
    }

    public void setMonthAddmoney(double monthAddmoney) {
        this.monthAddmoney = monthAddmoney;
    }

    public double getMonthRemovemoney() {
        return monthRemovemoney;
    }

    public void setMonthRemovemoney(double monthRemovemoney) {
        this.monthRemovemoney = monthRemovemoney;
    }

    public int getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(int recordMonth) {
        this.recordMonth = recordMonth;
    }
}
