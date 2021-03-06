package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.selection.SelectRecipeActivity;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;
import com.rabbit.green.baking.app.recipes.steps.single.SingleStepActivity;
import com.rabbit.green.baking.app.recipes.widget.config.RecipeWidgetConfigureActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
public abstract class ActivityContributorModule {
    @ContributesAndroidInjector
    abstract SelectRecipeActivity contributeSelectRecipeActivity();

    @ContributesAndroidInjector
    abstract StepsActivity contributeStepsActivity();

    @ContributesAndroidInjector
    abstract SingleStepActivity contributeSingleStepActivity();

    @ContributesAndroidInjector
    abstract RecipeWidgetConfigureActivity contributeRecipeWidgetConfigureActivity();
}
