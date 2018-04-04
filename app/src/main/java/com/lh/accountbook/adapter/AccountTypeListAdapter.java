package com.lh.accountbook.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.accountbook.R;
import com.lh.accountbook.bean.account.AccountEditTypeBean;
import com.lh.accountbook.databinding.ItemAccountTypeBinding;
import com.lh.accountbook.viewholder.AccountTypeViewHolder;

import java.util.List;

/**
 * Created by LuHao on 2018/3/23.
 * 账单类型
 */

public class AccountTypeListAdapter extends RecyclerView.Adapter<AccountTypeViewHolder> {
    private List<AccountEditTypeBean> data;
    public interface OnAccountTypeItemClick{
        void onItemClick(int position);
    }
    private OnAccountTypeItemClick onItemClickListener;

    public AccountTypeListAdapter(List<AccountEditTypeBean> data, OnAccountTypeItemClick onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public AccountTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAccountTypeBinding itemAccountTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_account_type, parent, false);
        return new AccountTypeViewHolder(itemAccountTypeBinding);
    }

    @Override
    public void onBindViewHolder(AccountTypeViewHolder holder, int position) {
        ItemAccountTypeBinding itemAccountTypeBinding = holder.itemAccountTypeBinding;
        AccountEditTypeBean accountEditTypeBean = data.get(position);
        itemAccountTypeBinding.setAccountedittype(accountEditTypeBean);
        itemAccountTypeBinding.setClick(new OnItemClickListener(position));
        itemAccountTypeBinding.imgIcon.setImageResource(accountEditTypeBean.getTypeIcon());
        itemAccountTypeBinding.executePendingBindings();
    }

    /**
     * item点击事件
     */
    public class OnItemClickListener {
        int position;

        OnItemClickListener(int position) {
            this.position = position;
        }

        public void onItemClick(View view) {
            if(onItemClickListener!=null)onItemClickListener.onItemClick(position);
        }
    }

}
