package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.recipes.steps.details.PlayerModule;
import com.rabbit.green.baking.app.recipes.steps.details.StepDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentContributorModule {
    @ContributesAndroidInjector(modules = PlayerModule.class)
    abstract StepDetailFragment contributeStepDetailFragment();
}
