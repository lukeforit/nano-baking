package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.data.source.net.NetworkModule;

import dagger.Module;

@Module(includes = NetworkModule.class)
public class AppModule {
}
