package com.rabbit.green.baking.app.recipes.steps.single.ingredients;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.common.adapter.BaseAdapter;
import com.rabbit.green.baking.app.data.model.Ingredient;

import javax.inject.Inject;

public class IngredientAdapter extends BaseAdapter<Ingredient> {

    @Inject
    public IngredientAdapter() {
    }

    @Override
    public void onViewHolderClick(int position) {
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_ingredient;
    }
}
