package com.lh.accountbook.bean.account;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/22.
 * 账单类型
 */
@DatabaseTable
public class AccountEditTypeBean {
    @DatabaseField(generatedId = true)
    private int typeId;
    @DatabaseField
    private String typeName;
    @DatabaseField
    private int typeIcon;

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
}
