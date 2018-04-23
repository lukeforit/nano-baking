package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.steps.single.details.StepDetailsFragment;
import com.rabbit.green.baking.app.recipes.steps.single.ingredients.IngredientsFragment;
import com.rabbit.green.baking.app.recipes.steps.single.details.PlayerModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentContributorModule {
    @ContributesAndroidInjector(modules = PlayerModule.class)
    abstract StepDetailsFragment contributeStepDetailFragment();

    @ContributesAndroidInjector
    abstract IngredientsFragment contributeIngredientsFragment();
}
