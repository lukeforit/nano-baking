package com.rabbit.green.baking.app.recipes.steps.details;

import android.os.Bundle;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.recipes.BaseActivity;

public class SingleStepActivity extends BaseActivity {

    public static final String BUNDLE_KEY_STEP = "BUNDLE_KEY_STEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_step);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(StepDetailFragment.ARG_STEP,
                    getIntent().getParcelableExtra(BUNDLE_KEY_STEP));
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_content, fragment)
                    .commit();
        }
    }
}
