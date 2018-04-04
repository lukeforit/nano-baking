package com.rabbit.green.baking.app.di;

import com.rabbit.green.baking.app.common.BakingApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityContributorModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent {
    void inject(BakingApp bakingApp);

    @Component.Builder
    interface Builder {
        AppComponent build();
        @BindsInstance
        Builder appModule(BakingApp bakingApp);
    }
}
