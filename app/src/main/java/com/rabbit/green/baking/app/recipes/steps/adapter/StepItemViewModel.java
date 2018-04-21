package com.rabbit.green.baking.app.recipes.steps.adapter;

import android.databinding.BaseObservable;

import com.rabbit.green.baking.app.data.model.StepTitle;

public class StepItemViewModel extends BaseObservable {
    private StepTitle stepTitle;

    public StepTitle getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(StepTitle stepTitle) {
        this.stepTitle = stepTitle;
    }
}
