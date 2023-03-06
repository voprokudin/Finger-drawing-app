package com.jotit.mvc_evaluation_app;

import android.app.Application;


import androidx.annotation.UiThread;

import com.jotit.mvc_evaluation_app.common.dependencyinjection.application.ApplicationComponent;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.application.ApplicationModule;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.application.DaggerApplicationComponent;

public class EvaluationAppMvc extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationComponent().inject(this);
    }

    @UiThread
    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

}
