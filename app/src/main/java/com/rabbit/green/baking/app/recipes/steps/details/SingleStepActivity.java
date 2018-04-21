package com.rabbit.green.baking.app.recipes.steps.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.databinding.ActivitySingleStepBinding;
import com.rabbit.green.baking.app.recipes.BaseActivity;

import javax.inject.Inject;

public class SingleStepActivity extends BaseActivity {

    public static final String BUNDLE_KEY_RECIPE = "BUNDLE_KEY_RECIPE";
    public static final String BUNDLE_KEY_STEP_ID = "BUNDLE_KEY_STEP_ID";

    @Inject
    SingleStepViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySingleStepBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_single_step);
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            //TODO handle different data
//            arguments.putParcelable(StepDetailFragment.ARG_STEP,
//                    getIntent().getParcelableExtra(BUNDLE_KEY_STEP));
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_content, fragment)
                    .commit();
        }
    }
}
