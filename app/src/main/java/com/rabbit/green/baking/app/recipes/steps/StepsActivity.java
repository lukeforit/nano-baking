package com.rabbit.green.baking.app.recipes.steps;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.databinding.ActivityStepsBinding;
import com.rabbit.green.baking.app.recipes.BaseActivity;
import com.rabbit.green.baking.app.recipes.BaseFragment;
import com.rabbit.green.baking.app.recipes.steps.details.IngredientsFragment;
import com.rabbit.green.baking.app.recipes.steps.details.StepDetailFragment;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

public class StepsActivity extends BaseActivity {

    public static final String BUNDLE_KEY_RECIPE = "BUNDLE_KEY_RECIPE";

    @Inject
    StepsViewModel viewModel;

    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStepsBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_steps);
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(BUNDLE_KEY_RECIPE)) {
            viewModel.setup(Parcels.<Recipe>unwrap(intent.getParcelableExtra(BUNDLE_KEY_RECIPE)));
        }

        if (binding.stepDetailContent != null) {
            if (savedInstanceState == null) {
                initFragment();
            } else {
                //TODO restoring fragment
                fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(StepDetailFragment.TAG);

                //This can happen whe user navigates back to the activity in different orientation
                if (fragment == null) {
                    initFragment();
                }
            }

            viewModel.setMasterDetailMode(true);
        } else {
            viewModel.setMasterDetailMode(false);
        }
    }

    private void initFragment() {
        replaceFragment(viewModel.getIngredientList());
    }

    void replaceFragment(Step step) {
        fragment = StepDetailFragment.newInstance(step);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.step_detail_content, fragment, StepDetailFragment.TAG)
                .commit();
    }

    void replaceFragment(List<Ingredient> list) {
        fragment = IngredientsFragment.newInstance(list);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.step_detail_content, fragment, IngredientsFragment.TAG)
                .commit();
    }

    void updateStepDetailFragment(Step step) {
        if (fragment instanceof StepDetailFragment) {
            ((StepDetailFragment) fragment).setData(step);
        }
    }
}
