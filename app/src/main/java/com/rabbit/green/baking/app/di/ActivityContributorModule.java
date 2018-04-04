package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.ItemDetailActivity;
import com.rabbit.green.baking.app.ItemListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {
    @ContributesAndroidInjector
    abstract ItemListActivity contributeItemListActivity();
    @ContributesAndroidInjector
    abstract ItemDetailActivity contributeItemDetailActivity();
}
