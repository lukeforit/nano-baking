package com.rabbit.green.baking.app.recipes.steps.single.details;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import javax.inject.Inject;

import static android.content.Context.NOTIFICATION_SERVICE;

public class StepDetailsViewModel extends BaseViewModel {

    @SuppressWarnings("WeakerAccess")
    @Inject
    ExoPlayer exoPlayer;

    @SuppressWarnings("WeakerAccess")
    @Inject
    DataSource.Factory factory;

    @Inject
    Context context;

    @SuppressWarnings("WeakerAccess")
    @Inject
    MediaSessionCompat mediaSession;

    @SuppressWarnings("WeakerAccess")
    @Inject
    PlaybackStateCompat.Builder playbackStateBuilder;

    private NotificationManager notificationManager;

    private Step step;
    private long seekPosition;

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
        if (seekPosition > 0) {
            exoPlayer.seekTo(seekPosition);
        }
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onStop() {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.seekTo(0);
            }
        });
        mediaSession.setActive(true);

        exoPlayer.addListener(new Player.DefaultEventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playWhenReady && playbackState == Player.STATE_READY) {
                    playbackStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                            exoPlayer.getCurrentPosition(), 1f);
                } else if (playbackState == Player.STATE_READY) {
                    playbackStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                            exoPlayer.getCurrentPosition(), 1f);
                }
                mediaSession.setPlaybackState(playbackStateBuilder.build());
                //TODO it causes RemoteServiceException Bad notification posted from package
//                showNotification(playbackStateBuilder.build());
            }
        });
    }

    private void showNotification(PlaybackStateCompat state) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, context.getString(R.string.notification_channel));

        int notificationIcon;
        String notificationString;
        if(state.getState() == PlaybackStateCompat.STATE_PLAYING){
            notificationIcon = R.drawable.exo_controls_pause;
            notificationString = context.getString(R.string.player_pause);
        } else {
            notificationIcon = R.drawable.exo_controls_play;
            notificationString = context.getString(R.string.player_play);
        }


        NotificationCompat.Action playPauseAction = new NotificationCompat.Action(
                notificationIcon, notificationString,
                MediaButtonReceiver.buildMediaButtonPendingIntent(context,
                        PlaybackStateCompat.ACTION_PLAY_PAUSE));

        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, context.getClass()), 0);

        builder.setContentTitle(step.title())
                .setContentText(step.title())
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .addAction(playPauseAction)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSession.getSessionToken())
                        .setShowActionsInCompactView(0,1));


        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public long getPlayerCurrentPosition() {
        return exoPlayer.getCurrentPosition();
    }

    public void setSeekPosition(long seekPosition) {
        this.seekPosition = seekPosition;
    }

    public void releasePlayer() {
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
        mediaSession.setActive(false);
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

    public static class VideoMediaReceiver extends BroadcastReceiver {

        public VideoMediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO reference media session in non static context
//            MediaButtonReceiver.handleIntent(mediaSession, intent);
        }
    }
}
