package com.rabbit.green.baking.app.recipes.selection.adapter;

import android.content.Intent;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.common.adapter.BaseAdapter;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.recipes.selection.SelectRecipeActivity;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;

import org.parceler.Parcels;

import javax.inject.Inject;

public class RecipeAdapter extends BaseAdapter<Recipe> {

    @Inject
    SelectRecipeActivity activity;

    @Inject
    public RecipeAdapter() {
    }

    @Override
    public void onViewHolderClick(int position) {
        //TODO handle navigation by preparing activity scoped module and component
        Intent intent = new Intent(activity, StepsActivity.class);
        intent.putExtra(StepsActivity.BUNDLE_KEY_RECIPE, Parcels.wrap(data.get(position)));
        activity.startActivity(intent);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_recipe;
    }
}
