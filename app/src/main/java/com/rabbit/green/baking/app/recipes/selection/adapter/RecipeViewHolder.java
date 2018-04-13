package com.rabbit.green.baking.app.recipes.selection.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.data.model.Recipe;

class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ViewDataBinding binding;

    private OnViewHolderClickListener onViewHolderClickListener;

    RecipeViewHolder(ViewDataBinding binding, OnViewHolderClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.getRoot().setOnClickListener(this);
        onViewHolderClickListener = listener;
    }

    void bind(Recipe recipe) {
        binding.setVariable(BR.recipe, recipe);
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        onViewHolderClickListener.onViewHolderClick(getAdapterPosition());
    }
}
