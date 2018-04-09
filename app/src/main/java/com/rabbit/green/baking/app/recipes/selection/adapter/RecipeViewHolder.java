package com.rabbit.green.baking.app.recipes.selection.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.data.model.Recipe;

class RecipeViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    RecipeViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Recipe recipe) {
        binding.setVariable(BR.recipe, recipe);
        binding.executePendingBindings();
    }
}
