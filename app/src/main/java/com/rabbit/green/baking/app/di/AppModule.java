package com.rabbit.green.baking.app.di;

import android.content.Context;

import com.rabbit.green.baking.app.common.BakingApp;
import com.rabbit.green.baking.app.data.source.net.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@SuppressWarnings("WeakerAccess")
@Module(includes = NetworkModule.class)
public class AppModule {
    @Provides
    @Singleton
    public Context provideContext(BakingApp app) {
        return app.getBaseContext();
    }
}
