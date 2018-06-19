package com.rabbit.green.baking.app.data.source.net;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class HttpClientModule {

    @Singleton
    @Provides
    public LocalInterceptor provideLocalInterceptor(Context context) {
        return new LocalInterceptor(context);
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(LocalInterceptor localInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(localInterceptor)
                .build();
    }

}
