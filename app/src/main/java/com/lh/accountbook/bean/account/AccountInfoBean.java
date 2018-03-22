package com.lh.accountbook.bean.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * 账单
 */
@DatabaseTable
public class AccountInfoBean implements Parcelable {
    @DatabaseField(generatedId = true)
    private int accountId;
    private String accountType;//类型
    private int accountTypeIcon;//类型图标
    private double money;//金额
    private boolean isOut = true;//是否是支出；true,支出；false，收入
    private long createTime;//账单创建时间
    private String tips;//备注
    private long userId;//外键，用户id

    public AccountInfoBean(){}

    public AccountInfoBean(int accountTypeIcon,String accountType) {
        this.accountType = accountType;
        this.accountTypeIcon = accountTypeIcon;
    }

    protected AccountInfoBean(Parcel in) {
        accountId = in.readInt();
        accountType = in.readString();
        accountTypeIcon = in.readInt();
        money = in.readDouble();
        isOut = in.readByte() != 0;
        createTime = in.readLong();
        tips = in.readString();
        userId = in.readLong();
    }

    public static final Creator<AccountInfoBean> CREATOR = new Creator<AccountInfoBean>() {
        @Override
        public AccountInfoBean createFromParcel(Parcel in) {
            return new AccountInfoBean(in);
        }

        @Override
        public AccountInfoBean[] newArray(int size) {
            return new AccountInfoBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(accountId);
        dest.writeString(accountType);
        dest.writeInt(accountTypeIcon);
        dest.writeDouble(money);
        dest.writeByte((byte) (isOut ? 1 : 0));
        dest.writeLong(createTime);
        dest.writeString(tips);
        dest.writeLong(userId);
    }
}
