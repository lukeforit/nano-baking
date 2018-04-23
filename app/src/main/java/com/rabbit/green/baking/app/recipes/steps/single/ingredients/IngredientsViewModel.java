package com.rabbit.green.baking.app.recipes.steps.single.ingredients;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class IngredientsViewModel extends BaseViewModel {

    @Inject
    IngredientAdapter adapter;

    @Inject
    IngredientsFragment fragment;

    @Inject
    public IngredientsViewModel() {
    }

    public void setup(List<Ingredient> list) {
        adapter.setData(list);
    }

    public IngredientAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(fragment.getContext());
    }
}
