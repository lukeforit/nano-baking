package com.rabbit.green.baking.app.recipes.widget;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.common.adapter.BaseAdapter;
import com.rabbit.green.baking.app.data.model.Recipe;

import javax.inject.Inject;

public class RecipeConfigureAdapter extends BaseAdapter<Recipe> {

    private RecipeCallback callback;

    @Inject
    public RecipeConfigureAdapter() {
    }

    @Override
    public void onViewHolderClick(int position) {
        callback.onRecipeClick(data.get(position).getId());
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_recipe_configure;
    }

    public void setCallback(RecipeCallback callback) {
        this.callback = callback;
    }

    public interface RecipeCallback {
        void onRecipeClick(int id);
    }
}
