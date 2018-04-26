package com.rabbit.green.baking.app.recipes.steps;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import java.util.List;

import static com.rabbit.green.baking.app.recipes.steps.adapter.OnStepClickListener.INGREDIENT_STEP_ID;

public class BaseStepViewModel extends BaseViewModel {

    protected Recipe recipe;
    protected int currentStepId;

    public BaseStepViewModel() {
        currentStepId = INGREDIENT_STEP_ID;
    }

    public List<Ingredient> getIngredientList() {
        return recipe.getIngredients();
    }

    public int getCurrentStepId() {
        return currentStepId;
    }

    public void setCurrentStepId(int currentStepId) {
        this.currentStepId = currentStepId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Step getCurrentStep() {
        return recipe.getSteps().get(currentStepId);
    }
}
