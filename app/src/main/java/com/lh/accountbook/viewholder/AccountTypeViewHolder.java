package com.lh.accountbook.viewholder;

import android.support.v7.widget.RecyclerView;

import com.lh.accountbook.databinding.ItemAccountTypeBinding;

/**
 * Created by LuHao on 2018/3/23.
 * 账单类型
 */

public class AccountTypeViewHolder extends RecyclerView.ViewHolder {
    public ItemAccountTypeBinding itemAccountTypeBinding;

    public AccountTypeViewHolder(ItemAccountTypeBinding itemAccountTypeBinding) {
        super(itemAccountTypeBinding.getRoot());
        this.itemAccountTypeBinding = itemAccountTypeBinding;
    }
}
