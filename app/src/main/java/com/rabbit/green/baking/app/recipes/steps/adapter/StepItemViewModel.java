package com.rabbit.green.baking.app.recipes.steps.adapter;

import android.databinding.BaseObservable;

import com.rabbit.green.baking.app.data.model.StepTitle;

public class StepItemViewModel extends BaseObservable {
    private StepTitle stepTitle;

    public String getTitle() {
        return stepTitle.title();
    }

    public String getImageUrl() {
        return stepTitle.imageUrl();
    }

    public void setStepTitle(StepTitle stepTitle) {
        this.stepTitle = stepTitle;
    }
}
