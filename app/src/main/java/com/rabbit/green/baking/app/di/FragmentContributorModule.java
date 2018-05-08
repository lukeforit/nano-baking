package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.steps.single.details.PlayerModule;
import com.rabbit.green.baking.app.recipes.steps.single.details.StepDetailsFragment;
import com.rabbit.green.baking.app.recipes.steps.single.ingredients.IngredientsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentContributorModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = PlayerModule.class)
    abstract StepDetailsFragment contributeStepDetailFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract IngredientsFragment contributeIngredientsFragment();
}
