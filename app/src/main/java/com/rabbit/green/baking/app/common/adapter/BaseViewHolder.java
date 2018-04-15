package com.rabbit.green.baking.app.common.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rabbit.green.baking.app.BR;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ViewDataBinding binding;

    private OnViewHolderClickListener onViewHolderClickListener;

    BaseViewHolder(ViewDataBinding binding, OnViewHolderClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.getRoot().setOnClickListener(this);
        onViewHolderClickListener = listener;
    }

    void bind(T item) {
        binding.setVariable(BR.item, item);
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        onViewHolderClickListener.onViewHolderClick(getAdapterPosition());
    }
}
