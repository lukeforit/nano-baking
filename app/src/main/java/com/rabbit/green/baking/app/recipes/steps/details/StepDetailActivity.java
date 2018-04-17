package com.rabbit.green.baking.app.recipes.steps.details;

import android.os.Bundle;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.recipes.BaseActivity;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StepsActivity}.
 */
public class StepDetailActivity extends BaseActivity {

    public static final String BUNDLE_KEY_STEP = "BUNDLE_KEY_STEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

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
