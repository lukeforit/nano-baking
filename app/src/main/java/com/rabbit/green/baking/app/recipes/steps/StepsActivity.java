package com.rabbit.green.baking.app.recipes.steps;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.databinding.ActivityStepsBinding;
import com.rabbit.green.baking.app.recipes.BaseActivity;
import com.rabbit.green.baking.app.recipes.steps.details.StepDetailFragment;

import org.parceler.Parcels;

import javax.inject.Inject;

public class StepsActivity extends BaseActivity {

    public static final String BUNDLE_KEY_RECIPE = "BUNDLE_KEY_RECIPE";

    @Inject
    StepsViewModel viewModel;

    private StepDetailFragment fragment;

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
                fragment = new StepDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(
                        StepDetailFragment.ARG_STEP, Parcels.wrap(viewModel.getStep(0)));
                fragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.step_detail_content, fragment, StepDetailFragment.TAG)
                        .commit();
            } else {
                fragment = (StepDetailFragment) getSupportFragmentManager().findFragmentByTag(StepDetailFragment.TAG);
            }

            viewModel.setMasterDetailMode(true);
        } else {
            viewModel.setMasterDetailMode(false);
        }

    }

    void updateStepDetailFragment(Step step) {
        fragment.setData(step);
    }
}
