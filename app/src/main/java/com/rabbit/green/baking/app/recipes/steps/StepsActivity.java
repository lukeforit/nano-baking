package com.rabbit.green.baking.app.recipes.steps;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.databinding.ActivityStepsBinding;
import com.rabbit.green.baking.app.recipes.BaseFragment;

import org.parceler.Parcels;

import javax.inject.Inject;

public class StepsActivity extends BaseStepActivity {

    public static final String BUNDLE_KEY_RECIPE = "BUNDLE_KEY_RECIPE";
    public static final String BUNDLE_KEY_CURRENT_ID = "BUNDLE_KEY_CURRENT_ID";

    @Inject
    StepsViewModel viewModel;

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
                viewModel.setCurrentStepId(savedInstanceState.getInt(
                        BUNDLE_KEY_CURRENT_ID, viewModel.getCurrentStepId()));
                fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(BaseFragment.TAG);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_CURRENT_ID, viewModel.getCurrentStepId());
    }

    private void initFragment() {
        replaceFragment(viewModel.getIngredientList());
    }
}
