package com.jotit.mvc_evaluation_app.common.dependencyinjection.controller;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewMvcModule {

    @Provides
    LayoutInflater layoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

}
