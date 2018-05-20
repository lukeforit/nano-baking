package com.rabbit.green.baking.app.recipes.steps.single.details;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

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

//TODO apply scope
@Module
public class PlayerModule {

    private static final String TAG = PlayerModule.class.getName() + "-MediaSession";

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

    @Provides
    public PlaybackStateCompat.Builder providePlaybackStateCompatBuilder() {
        return new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PAUSE
                        | PlaybackStateCompat.ACTION_PLAY_PAUSE);
    }

    @Provides
    public MediaSessionCompat provideMediaSessionCompat(Context context,
                                                        PlaybackStateCompat.Builder builder) {
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, TAG);
        mediaSessionCompat.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
                | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSessionCompat.setMediaButtonReceiver(null);
        mediaSessionCompat.setPlaybackState(builder.build());
        return mediaSessionCompat;
    }
}
