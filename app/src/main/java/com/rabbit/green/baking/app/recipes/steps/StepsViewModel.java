package com.rabbit.green.baking.app.recipes.steps;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.recipes.BaseViewModel;
import com.rabbit.green.baking.app.recipes.steps.adapter.OnStepClickListener;
import com.rabbit.green.baking.app.recipes.steps.adapter.StepsAdapter;
import com.rabbit.green.baking.app.recipes.steps.details.SingleStepActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import static com.rabbit.green.baking.app.recipes.steps.adapter.OnStepClickListener.INGREDIENT_STEP_ID;

public class StepsViewModel extends BaseViewModel {

    @Inject
    StepsActivity activity;

    @Inject
    StepsAdapter adapter;

    private Recipe recipe;
    private int currentStepId;
    private OnStepClickListener listener;

    @Inject
    public StepsViewModel() {
        currentStepId = INGREDIENT_STEP_ID;
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

    List<Ingredient> getIngredientList() {
        return recipe.getIngredients();
    }

    int getCurrentStepId() {
        return currentStepId;
    }

    void setCurrentStepId(int currentStepId) {
        this.currentStepId = currentStepId;
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
        currentStepId = detailId;
        Intent intent = new Intent(activity, SingleStepActivity.class);
        intent.putExtra(SingleStepActivity.BUNDLE_KEY_RECIPE, Parcels.wrap(recipe));
        intent.putExtra(SingleStepActivity.BUNDLE_KEY_STEP_ID, detailId);
        activity.startActivity(intent);
    }

    private void updateDetailsView(int detailId) {
        if (currentStepId != detailId) {
            if (detailId == INGREDIENT_STEP_ID) {
                activity.replaceFragment(recipe.getIngredients());
            } else if (currentStepId == INGREDIENT_STEP_ID) {
                activity.replaceFragment(recipe.getSteps().get(detailId));
            } else {
                activity.updateStepDetailFragment(recipe.getSteps().get(detailId));
            }
            currentStepId = detailId;
        }
    }
}
