package com.rabbit.green.baking.app.recipes.steps.details;

import com.google.android.exoplayer2.ExoPlayer;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import javax.inject.Inject;

public class StepDetailsViewModel extends BaseViewModel {

    @Inject
    ExoPlayer exoPlayer;

    private Step step;

    @Inject
    public StepDetailsViewModel() {
    }

    public void setData(Step step) {
        this.step = step;
        notifyChange();
    }

    public String getDescription() {
        return step != null ? step.getDescription() : "";
    }

    public String getThumbnailUrl() {
        return step.getThumbnailURL();
    }

    public String getId() {
        return String.valueOf(step.getId());
    }

    public String getVideoUrl() {
        return step.getVideoURL();
    }
}
