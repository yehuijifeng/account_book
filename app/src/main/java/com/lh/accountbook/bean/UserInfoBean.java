package com.lh.accountbook.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * 用户表
 */
@DatabaseTable
public class UserInfoBean implements Parcelable {
    @DatabaseField(id = true)
    private long userId;//用户id
    @DatabaseField
    private String userName;//用户名
    @DatabaseField
    private String userIcon;//头像
    @DatabaseField
    private long userRegisterTime;//注册时间


    protected UserInfoBean(Parcel in) {
        userId = in.readLong();
        userName = in.readString();
        userIcon = in.readString();
        userRegisterTime = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(userName);
        dest.writeString(userIcon);
        dest.writeLong(userRegisterTime);
    }

    public UserInfoBean() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public long getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(long userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel in) {
            return new UserInfoBean(in);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };

}
