package com.rabbit.green.baking.app.recipes.steps.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.common.adapter.OnViewHolderClickListener;
import com.rabbit.green.baking.app.data.model.StepTitle;

public class StepItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final StepItemViewModel viewModel;
    @SuppressWarnings("FieldCanBeLocal")
    private final ViewDataBinding binding;
    private final OnViewHolderClickListener onViewHolderClickListener;

    public StepItemViewHolder(ViewDataBinding binding, OnViewHolderClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.getRoot().setOnClickListener(this);
        this.onViewHolderClickListener = listener;
        this.viewModel = new StepItemViewModel();
        this.binding.setVariable(BR.item, viewModel);
    }

    public void bind(StepTitle item) {
        viewModel.setStepTitle(item);
        viewModel.notifyChange();
    }

    @Override
    public void onClick(View v) {
        onViewHolderClickListener.onViewHolderClick(getAdapterPosition());
    }
}
