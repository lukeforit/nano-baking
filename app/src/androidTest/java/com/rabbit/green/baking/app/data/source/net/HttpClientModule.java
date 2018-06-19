package com.rabbit.green.baking.app.data.source.net;

import android.content.Context;

import com.jakewharton.espresso.OkHttp3IdlingResource;

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

    @Singleton
    @Provides
    public OkHttp3IdlingResource provideOkHttp3IdlingResource(OkHttpClient okHttpClient) {
        return OkHttp3IdlingResource.create("okHttpIR", okHttpClient);
    }

}
