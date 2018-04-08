package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.details.ItemDetailActivity;
import com.rabbit.green.baking.app.recipes.selection.SelectRecipeActivity;
import com.rabbit.green.baking.app.recipes.steps.ItemListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {
    @ContributesAndroidInjector
    abstract SelectRecipeActivity contributeSelectRecipeActivity();
    @ContributesAndroidInjector
    abstract ItemListActivity contributeItemListActivity();
    @ContributesAndroidInjector
    abstract ItemDetailActivity contributeItemDetailActivity();
}
