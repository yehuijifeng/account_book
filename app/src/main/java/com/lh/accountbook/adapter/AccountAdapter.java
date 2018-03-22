package com.lh.accountbook.adapter;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.accountbook.R;
import com.lh.accountbook.bean.account.AccountBudgetBean;
import com.lh.accountbook.bean.account.AccountInfoBean;
import com.lh.accountbook.databinding.ItemAccountBinding;
import com.lh.accountbook.databinding.ItemAccountHanderBinding;
import com.lh.accountbook.viewholder.AccountViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuHao on 2018/3/20.
 * 账单
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {
    private List<Object> data;

    public AccountAdapter(AccountBudgetBean accountBudgetBean, List<Object> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(0, accountBudgetBean);
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
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        return super.getItemViewType(position);
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup paernt, int viewType) {
        if (viewType == 1) {
            ItemAccountHanderBinding itemAccountHanderBinding = DataBindingUtil.inflate(LayoutInflater.from(paernt.getContext()), R.layout.item_account_hander, paernt, false);
            return new AccountViewHolder(itemAccountHanderBinding);
        }
        ItemAccountBinding itemAccountBinding = DataBindingUtil.inflate(LayoutInflater.from(paernt.getContext()), R.layout.item_account, paernt, false);
        return new AccountViewHolder(itemAccountBinding);
    }


    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        if (position == 0) {
            ItemAccountHanderBinding itemAccountHanderBinding = holder.itemAccountHanderBinding;
            AccountBudgetBean accountBudgetBean = (AccountBudgetBean) data.get(position);
            itemAccountHanderBinding.setBudgetinfo(accountBudgetBean);
            itemAccountHanderBinding.executePendingBindings();
        } else {
            ItemAccountBinding itemAccountBinding = holder.itemAccountBinding;
            AccountInfoBean accountInfoBean = (AccountInfoBean) data.get(position);
            itemAccountBinding.getRoot().setTag(position);
            itemAccountBinding.setAccountinfo(accountInfoBean);
            itemAccountBinding.setOnItemUpdateClickListener(new OnItemUpdateClickListener(position, accountInfoBean, itemAccountBinding));
            itemAccountBinding.imgBianji.setVisibility(View.GONE);
            itemAccountBinding.imgShanchu.setVisibility(View.GONE);
            itemAccountBinding.imgIcon.setImageResource(accountInfoBean.getAccountTypeIcon());
            itemAccountBinding.executePendingBindings();
        }
    }

    /**
     * item中按钮点击事件
     */
    public class OnItemUpdateClickListener {
        private AccountInfoBean accountInfoBean;
        private ItemAccountBinding itemAccountBinding;
        private int position;

        private OnItemUpdateClickListener(int position, AccountInfoBean accountInfoBean, ItemAccountBinding itemAccountBinding) {
            this.position = position;
            this.accountInfoBean = accountInfoBean;
            this.itemAccountBinding = itemAccountBinding;
        }

        public void bianji(View v) {
            accountInfoBean.setAccountType("修改过的账单类型");
            data.set(position, accountInfoBean);
            notifyDataSetChanged();
        }

        public void shanchu(View v) {
            Snackbar.make(v, "这里有一个删除账单的操作", Snackbar.LENGTH_LONG)
                    .setAction("删除", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            data.remove(position);
                            notifyDataSetChanged();
                        }
                    }).show();
        }

        public void showUpdate(View v) {
            if (itemAccountBinding.imgBianji.getVisibility() == View.VISIBLE) {
                itemAccountBinding.imgBianji.setVisibility(View.GONE);
                itemAccountBinding.imgShanchu.setVisibility(View.GONE);
            } else {
                itemAccountBinding.imgBianji.setVisibility(View.VISIBLE);
                itemAccountBinding.imgShanchu.setVisibility(View.VISIBLE);
            }
        }

    }
}
