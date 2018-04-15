package com.rabbit.green.baking.app.recipes.steps;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.recipes.BaseViewModel;

import javax.inject.Inject;

public class StepsViewModel extends BaseViewModel {

    @Inject
    StepsActivity activity;

    @Inject
    StepsAdapter adapter;

    @Inject
    public StepsViewModel() {
    }

    public void setup() {
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    public StepsAdapter getAdapter() {
        return adapter;
    }
}
