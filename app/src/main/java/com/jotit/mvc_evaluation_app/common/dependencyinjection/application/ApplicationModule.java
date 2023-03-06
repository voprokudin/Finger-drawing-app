package com.jotit.mvc_evaluation_app.common.dependencyinjection.application;

import android.app.Application;


import com.techyourchance.threadposter.BackgroundThreadPoster;
import com.techyourchance.threadposter.UiThreadPoster;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    Application application() {
        return application;
    }


    @Provides
    @ApplicationScope
    UiThreadPoster uiThreadPoster() {
        return new UiThreadPoster();
    }

    @Provides
    @ApplicationScope
    BackgroundThreadPoster backgroundThreadPoster() {
        return new BackgroundThreadPoster();
    }


}
