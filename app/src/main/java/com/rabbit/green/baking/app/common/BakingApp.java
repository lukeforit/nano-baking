package com.rabbit.green.baking.app.common;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.rabbit.green.baking.app.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import okhttp3.OkHttpClient;

public class BakingApp extends Application implements HasActivityInjector {

    @SuppressWarnings("WeakerAccess")
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    protected OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .appModule(this).build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @VisibleForTesting
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
