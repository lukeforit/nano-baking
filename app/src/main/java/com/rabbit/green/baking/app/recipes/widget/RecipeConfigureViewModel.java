package com.rabbit.green.baking.app.recipes.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.source.local.RecipesLocalDataStore;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import javax.inject.Inject;

public class RecipeConfigureViewModel extends BaseViewModel implements RecipeConfigureAdapter.RecipeCallback {

    @SuppressWarnings("WeakerAccess")
    @Inject
    RecipeWidgetConfigureActivity activity;

    @SuppressWarnings("WeakerAccess")
    @Inject
    RecipeConfigureAdapter adapter;

    @Inject
    RecipesLocalDataStore localDataStore;

    @Inject
    public RecipeConfigureViewModel() {
    }

    public void setup() {
        adapter.setData(localDataStore.getRecipes());
        adapter.setCallback(this);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(activity, activity.getResources().getInteger(R.integer.grid_span_count));
    }

    public RecipeConfigureAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onRecipeClick(int id) {
        activity.updateWidget(id);
    }
}
