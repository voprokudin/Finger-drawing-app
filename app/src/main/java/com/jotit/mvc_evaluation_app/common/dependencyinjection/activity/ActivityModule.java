package com.jotit.mvc_evaluation_app.common.dependencyinjection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.jotit.mvc_evaluation_app.R;
import com.jotit.mvc_evaluation_app.screens.ScreensNavigator;
import com.jotit.mvc_evaluation_app.screens.common.ViewMvcFactory;
import com.ncapdevi.fragnav.FragNavController;
import com.techyourchance.threadposter.UiThreadPoster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    Context context() {
        return activity;
    }

    @Provides
    AppCompatActivity appCompatActivity() {
        return activity;
    }

    @Provides
    Activity activity() {
        return activity;
    }

    @Provides
    Resources getResources(Activity activity) {
        return activity.getResources();
    }

    @Provides
    FragmentManager fragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    FragNavController fragNavController(FragmentManager fragmentManager) {
        return new FragNavController(fragmentManager, R.id.fragmentContainerMain);
    }

    @Provides
    @ActivityScope
    ScreensNavigator screensNavigator(Activity activity, FragNavController fragNavController) {
        return new ScreensNavigator(activity, fragNavController);
    }
}
