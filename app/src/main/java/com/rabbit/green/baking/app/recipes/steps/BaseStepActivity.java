package com.rabbit.green.baking.app.recipes.steps;

import android.annotation.SuppressLint;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseActivity;
import com.rabbit.green.baking.app.recipes.BaseFragment;
import com.rabbit.green.baking.app.recipes.steps.single.details.StepDetailsFragment;
import com.rabbit.green.baking.app.recipes.steps.single.ingredients.IngredientsFragment;

import java.util.List;

@SuppressLint("Registered")
public class BaseStepActivity extends BaseActivity {

    protected static final String BUNDLE_KEY_CURRENT_ID = "BUNDLE_KEY_CURRENT_ID";

    protected BaseFragment fragment;

    public void replaceFragment(Step step) {
        fragment = StepDetailsFragment.newInstance(step);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.step_detail_content, fragment, BaseFragment.TAG)
                .commit();
    }

    public void replaceFragment(List<Ingredient> list) {
        fragment = IngredientsFragment.newInstance(list);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.step_detail_content, fragment, BaseFragment.TAG)
                .commit();
    }

    public void updateStepDetailFragment(Step step) {
        if (fragment instanceof StepDetailsFragment) {
            ((StepDetailsFragment) fragment).setData(step);
        }
    }
}
