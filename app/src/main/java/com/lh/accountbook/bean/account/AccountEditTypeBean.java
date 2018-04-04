package com.lh.accountbook.bean.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/22.
 * 账单类型
 */
@DatabaseTable
public class AccountEditTypeBean implements Parcelable {
    @DatabaseField(generatedId = true)
    private int typeId;
    @DatabaseField
    private String typeName;//账单类型名称
    @DatabaseField
    private int typeIcon;
    public AccountEditTypeBean(){}
    protected AccountEditTypeBean(Parcel in) {
        typeId = in.readInt();
        typeName = in.readString();
        typeIcon = in.readInt();
    }

    public static final Creator<AccountEditTypeBean> CREATOR = new Creator<AccountEditTypeBean>() {
        @Override
        public AccountEditTypeBean createFromParcel(Parcel in) {
            return new AccountEditTypeBean(in);
        }

        @Override
        public AccountEditTypeBean[] newArray(int size) {
            return new AccountEditTypeBean[size];
        }
    };

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(int typeIcon) {
        this.typeIcon = typeIcon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(typeId);
        dest.writeString(typeName);
        dest.writeInt(typeIcon);
    }
}
