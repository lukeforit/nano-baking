package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.steps.single.SingleStepActivity;
import com.rabbit.green.baking.app.recipes.selection.SelectRecipeActivity;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {
    @ContributesAndroidInjector
    abstract SelectRecipeActivity contributeSelectRecipeActivity();
    @ContributesAndroidInjector
    abstract StepsActivity contributeStepsActivity();
    @ContributesAndroidInjector
    abstract SingleStepActivity contributeSingleStepActivity();
}
