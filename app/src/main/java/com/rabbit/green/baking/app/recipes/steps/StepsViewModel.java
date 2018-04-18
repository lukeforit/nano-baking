package com.rabbit.green.baking.app.recipes.steps;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseViewModel;
import com.rabbit.green.baking.app.recipes.steps.details.SingleStepActivity;

import org.parceler.Parcels;

import javax.inject.Inject;

import static com.rabbit.green.baking.app.recipes.steps.details.SingleStepActivity.BUNDLE_KEY_STEP;

public class StepsViewModel extends BaseViewModel {

    @Inject
    StepsActivity activity;

    @Inject
    StepsAdapter adapter;

    private Recipe recipe;
    private OnStepClickListener listener;

    @Inject
    public StepsViewModel() {
    }

    public void setup(Recipe recipe) {
        this.recipe = recipe;
        adapter.setData(recipe.getSteps());
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    public StepsAdapter getAdapter() {
        return adapter;
    }

    Step getStep(int id) {
        return recipe.getSteps().get(id);
    }

    public void setMasterDetailMode(boolean masterDetailMode) {
        if (masterDetailMode) {
            listener = new OnStepClickListener() {
                @Override
                public void onStepClick(int id) {
                    updateDetailsView(id);
                }
            };
        } else {
            listener = new OnStepClickListener() {
                @Override
                public void onStepClick(int id) {
                    navigateToDetailsActivity(id);
                }
            };
        }
        adapter.setListener(listener);
    }

    private void navigateToDetailsActivity(int detailId) {
        Intent intent = new Intent(activity, SingleStepActivity.class);
        intent.putExtra(BUNDLE_KEY_STEP, Parcels.wrap(recipe.getSteps().get(detailId)));
        activity.startActivity(intent);
    }

    private void updateDetailsView(int detailId) {
        activity.updateStepDetailFragment(recipe.getSteps().get(detailId));
    }
}
