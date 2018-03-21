package com.lh.accountbook.bean.asset;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LuHao on 2018/3/19.
 * 资产明细
 */
@DatabaseTable
public class AssetContentBean {
    @DatabaseField(generatedId = true)
    private int assetContentId;
    private int assetId;//外键，这条明细记录属于哪一个资产
    private double money;
    private String userName;//目标人
    private long createTime;//记录创建时间
    private String remark;//备注
    private boolean isHarvest;//是否失效，默认0，未失效；

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isHarvest() {
        return isHarvest;
    }

    public void setHarvest(boolean harvest) {
        isHarvest = harvest;
    }
}
