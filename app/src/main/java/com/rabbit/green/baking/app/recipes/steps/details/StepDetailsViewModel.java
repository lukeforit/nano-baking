package com.rabbit.green.baking.app.recipes.steps.details;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import javax.inject.Inject;

public class StepDetailsViewModel extends BaseViewModel {

    @Inject
    ExoPlayer exoPlayer;
    @Inject
    DataSource.Factory factory;

    @Inject
    Context context;

    private Step step;

    @Inject
    public StepDetailsViewModel() {
    }

    public void setData(Step step) {
        this.step = step;
        preparePlayer();
        notifyChange();
    }

    public void preparePlayer() {
        MediaSource mediaSource = new ExtractorMediaSource.Factory(factory)
                .createMediaSource(Uri.parse(step.getVideoURL()));
        exoPlayer.prepare(mediaSource);
    }

    public void releasePlayer() {
        exoPlayer.release();
    }

    public Step getStep() {
        return step;
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

    public int getPlayerVisibility() {
        return step == null || TextUtils.isEmpty(step.getVideoURL()) ? View.GONE : View.VISIBLE;
    }

    public ExoPlayer getExoPlayer() {
        return exoPlayer;
    }
}
