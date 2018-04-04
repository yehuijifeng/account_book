package com.lh.accountbook.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.accountbook.R;
import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.databinding.ItemAccountEditTypeBinding;
import com.lh.accountbook.viewholder.AccountEditViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuHao on 2018/3/20.
 * 账单类型
 */

public class AccountEditAdapter extends RecyclerView.Adapter<AccountEditViewHolder> {
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    protected List<AccountEditTypeBean> data;

    public AccountEditAdapter(List<AccountEditTypeBean> data, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public AccountEditViewHolder onCreateViewHolder(ViewGroup paernt, int viewType) {
        ItemAccountEditTypeBinding itemAccountEditTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(paernt.getContext()), R.layout.item_account_edit_type, paernt, false);
        return new AccountEditViewHolder(itemAccountEditTypeBinding);
    }


    @Override
    public void onBindViewHolder(AccountEditViewHolder holder, int position) {
        ItemAccountEditTypeBinding itemAccountEditTypeBinding = holder.itemAccountEditTypeBinding;
        AccountEditTypeBean accountEditTypeBean = data.get(position);
        itemAccountEditTypeBinding.getRoot().setTag(position);
        itemAccountEditTypeBinding.setAccountedittype(accountEditTypeBean);
        itemAccountEditTypeBinding.setItemclick(new OnItemViewClickListener(position));
        itemAccountEditTypeBinding.imgIcon.setImageResource(accountEditTypeBean.getTypeIcon());
        itemAccountEditTypeBinding.executePendingBindings();
    }

    /**
     * item点击事件
     */
    public class OnItemViewClickListener   {
        int position;

        OnItemViewClickListener(int position) {
            this.position = position;
        }

        public void onClick(View v) {
            if (onItemClickListener != null) onItemClickListener.onItemClick(position, v);
        }
    }
}
