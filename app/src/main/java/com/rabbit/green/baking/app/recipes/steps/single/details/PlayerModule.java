package com.rabbit.green.baking.app.recipes.steps.single.details;

import android.content.Context;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.rabbit.green.baking.app.R;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerModule {
    @Provides
    public ExoPlayer provideExoPlayer(Context context) {
        return ExoPlayerFactory.newSimpleInstance(context,
                new DefaultTrackSelector(
                        new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
    }

    @Provides
    public DataSource.Factory provideDataSourceFactory(Context context) {
        return new DefaultDataSourceFactory(context, context.getString(R.string.app_name));
    }
}
