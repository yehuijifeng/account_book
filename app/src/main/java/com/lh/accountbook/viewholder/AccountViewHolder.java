package com.lh.accountbook.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by LuHao on 2018/3/21.
 * 账单viewholder
 */

public class AccountViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public T viewDataBinding;

    //实现的方法
    public AccountViewHolder(T t) {
        super(t.getRoot());
        viewDataBinding = t;
    }
}
