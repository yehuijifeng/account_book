package com.lh.accountbook.viewholder;

import android.support.v7.widget.RecyclerView;

import com.lh.accountbook.databinding.ItemAccountEditTypeBinding;

/**
 * Created by LuHao on 2018/3/22.
 * 账单类型
 */

public class AccountEditViewHolder extends RecyclerView.ViewHolder {
    public ItemAccountEditTypeBinding itemAccountEditTypeBinding;

    public AccountEditViewHolder(ItemAccountEditTypeBinding itemAccountEditTypeBinding) {
        super(itemAccountEditTypeBinding.getRoot());
        this.itemAccountEditTypeBinding = itemAccountEditTypeBinding;
    }
}
