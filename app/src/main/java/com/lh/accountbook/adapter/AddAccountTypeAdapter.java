package com.lh.accountbook.adapter;

import android.view.View;

import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.databinding.ItemAccountEditTypeBinding;
import com.lh.accountbook.viewholder.AccountEditViewHolder;

import java.util.List;

/**
 * Created by LuHao on 2018/4/4.
 */

public class AddAccountTypeAdapter extends AccountEditAdapter {

    public AddAccountTypeAdapter(List<AccountEditTypeBean> data, OnItemClickListener onItemClickListener) {
        super(data, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(AccountEditViewHolder holder, int position) {
        ItemAccountEditTypeBinding itemAccountEditTypeBinding = holder.itemAccountEditTypeBinding;
        AccountEditTypeBean accountEditTypeBean = data.get(position);
        itemAccountEditTypeBinding.getRoot().setTag(position);
        itemAccountEditTypeBinding.setAccountedittype(accountEditTypeBean);
        itemAccountEditTypeBinding.setItemclick(new OnItemViewClickListener(position));
        itemAccountEditTypeBinding.textName.setVisibility(View.GONE);
        itemAccountEditTypeBinding.imgIcon.setImageResource(accountEditTypeBean.getTypeIcon());
        itemAccountEditTypeBinding.executePendingBindings();
    }
}
