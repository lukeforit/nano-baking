package com.rabbit.green.baking.app.recipes.steps.adapter;

public interface OnStepClickListener {
    /**
     * Represents ingredients ID (out of the steps list data)
     */
    int INGREDIENT_STEP_ID = -1;

    /**
     * On click callback for recipe's steps.
     *
     * @param id - ID within the steps list or {@link OnStepClickListener#INGREDIENT_STEP_ID}
     */
    void onStepClick(int id);
}
