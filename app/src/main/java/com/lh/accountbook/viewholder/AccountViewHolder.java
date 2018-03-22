package com.lh.accountbook.viewholder;

import android.support.v7.widget.RecyclerView;

import com.lh.accountbook.databinding.ItemAccountBinding;
import com.lh.accountbook.databinding.ItemAccountHanderBinding;

/**
 * Created by LuHao on 2018/3/21.
 * 账单viewholder
 */

public class AccountViewHolder extends RecyclerView.ViewHolder {

    public ItemAccountBinding itemAccountBinding;
    public ItemAccountHanderBinding itemAccountHanderBinding;

    //实现的方法
    public AccountViewHolder(ItemAccountBinding t) {
        super(t.getRoot());
        itemAccountBinding = t;
    }

    //实现的方法
    public AccountViewHolder(ItemAccountHanderBinding t) {
        super(t.getRoot());
        itemAccountHanderBinding = t;
    }
}
