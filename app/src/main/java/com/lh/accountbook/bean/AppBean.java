package com.lh.accountbook.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * app数据
 */
@DatabaseTable
public class AppBean implements Parcelable {
    @DatabaseField(id = true)
    private int appId = 1;//默认1
    @DatabaseField
    private int isLock;//是否启动app验证。0，不验证；1，验证

    public AppBean() {
    }

    protected AppBean(Parcel in) {
        appId = in.readInt();
        isLock = in.readInt();
    }

    public static final Creator<AppBean> CREATOR = new Creator<AppBean>() {
        @Override
        public AppBean createFromParcel(Parcel in) {
            return new AppBean(in);
        }

        @Override
        public AppBean[] newArray(int size) {
            return new AppBean[size];
        }
    };

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(appId);
        dest.writeInt(isLock);
    }
}
