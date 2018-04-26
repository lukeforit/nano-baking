package com.rabbit.green.baking.app.recipes.steps.single;

import android.view.View;

import com.rabbit.green.baking.app.recipes.steps.BaseStepViewModel;
import com.rabbit.green.baking.app.recipes.steps.adapter.OnStepClickListener;

import javax.inject.Inject;

import static com.rabbit.green.baking.app.recipes.steps.adapter.OnStepClickListener.INGREDIENT_STEP_ID;

public class SingleStepViewModel extends BaseStepViewModel {

    @Inject
    SingleStepActivity activity;

    @Inject
    public SingleStepViewModel() {
    }

    public void onPreviousClick(@SuppressWarnings("unused") View view) {
        updateDetailsView(currentStepId - 1);
    }

    public void onNextClick(@SuppressWarnings("unused") View view) {
        updateDetailsView(currentStepId + 1);
    }

    public boolean isPreviousButtonEnabled() {
        return currentStepId > OnStepClickListener.INGREDIENT_STEP_ID;
    }

    public boolean isNextButtonEnabled() {
        return recipe != null && currentStepId < recipe.getSteps().size() - 1;
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
            notifyChange();
        }
    }
}
