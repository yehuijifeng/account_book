package com.lh.accountbook.bean.asset;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * 资产
 */
@DatabaseTable
public class AssetInfoBean {
    @DatabaseField(generatedId = true)
    private int assetId;
    @DatabaseField
    private double money;//资产金额
    @DatabaseField
    private String assetType;//资产描述
    @DatabaseField
    private int assetTypeIcon;//图标
    @DatabaseField
    private boolean isTag = true;//是否标记，用户标记后会纳入总资产的计算，默认true标记
    private int userId;

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public int getAssetTypeIcon() {
        return assetTypeIcon;
    }

    public void setAssetTypeIcon(int assetTypeIcon) {
        this.assetTypeIcon = assetTypeIcon;
    }

    public boolean isTag() {
        return isTag;
    }

    public void setTag(boolean tag) {
        isTag = tag;
    }
}
