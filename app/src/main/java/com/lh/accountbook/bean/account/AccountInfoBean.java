package com.lh.accountbook.bean.account;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * 账单
 */
@DatabaseTable
public class AccountInfoBean {
    @DatabaseField(generatedId = true)
    private int accountId;
    private String accountType;//类型
    private int accountTypeIcon;//类型图标
    private double money;//金额
    private boolean isOut=true;//是否是支出；true,支出；false，收入
    private long createTime;//账单创建时间
    private String tips;//备注
    private long userId;//外键，用户id

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountTypeIcon() {
        return accountTypeIcon;
    }

    public void setAccountTypeIcon(int accountTypeIcon) {
        this.accountTypeIcon = accountTypeIcon;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
