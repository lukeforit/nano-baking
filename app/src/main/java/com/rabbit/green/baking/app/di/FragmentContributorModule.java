package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.selection.SelectRecipeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentContributorModule {
    @ContributesAndroidInjector
    abstract SelectRecipeFragment contributeSelectRecipeFragment();
}
