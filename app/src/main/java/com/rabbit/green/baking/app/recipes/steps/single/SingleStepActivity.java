package com.rabbit.green.baking.app.recipes.steps.single;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.databinding.ActivitySingleStepBinding;
import com.rabbit.green.baking.app.recipes.steps.BaseStepActivity;

import org.parceler.Parcels;

import javax.inject.Inject;

import static com.rabbit.green.baking.app.recipes.steps.adapter.OnStepClickListener.INGREDIENT_STEP_ID;

public class SingleStepActivity extends BaseStepActivity {

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
            Intent intent = getIntent();
            if (intent != null
                    && intent.hasExtra(BUNDLE_KEY_RECIPE) && intent.hasExtra(BUNDLE_KEY_STEP_ID)) {
                viewModel.setCurrentStepId(
                        intent.getIntExtra(BUNDLE_KEY_STEP_ID, INGREDIENT_STEP_ID));
                viewModel.setRecipe(
                        Parcels.<Recipe>unwrap(intent.getParcelableExtra(BUNDLE_KEY_RECIPE)));
                if (viewModel.getCurrentStepId() == INGREDIENT_STEP_ID) {
                    replaceFragment(viewModel.getIngredientList());
                } else {
                    replaceFragment(viewModel.getCurrentStep());
                }
            }
        }
    }
}
