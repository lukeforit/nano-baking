package com.rabbit.green.baking.app.data.source.net;

import com.rabbit.green.baking.app.data.source.IDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = HttpClientModule.class)
public class NetworkModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public IDataRestService provideDataRestService(Retrofit retrofit) {
        return retrofit.create(IDataRestService.class);
    }

    @Singleton
    @Provides
    public IDataSource provideDataSource(IDataRestService service) {
        return new NetDataSource(service);
    }
}
