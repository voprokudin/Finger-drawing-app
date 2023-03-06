package com.jotit.mvc_evaluation_app.screens.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.jotit.mvc_evaluation_app.R;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.activity.ActivityComponent;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.activity.ActivityModule;
import com.jotit.mvc_evaluation_app.EvaluationAppMvc;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ControllerComponent;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ControllerModule;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ViewMvcModule;
import com.jotit.mvc_evaluation_app.screens.ScreensNavigator;


public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    protected static final String INTENT_EXTRA_SCREEN = "INTENT_EXTRA_SCREEN";
    private final static String UPDATE_PROMPT_ID = "UPDATE_PROMPT_ID";

    private ActivityComponent activityComponent;

    private boolean isControllerComponentUsed = false;

    @Inject ScreensNavigator screensNavigator;

    @UiThread
    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ((EvaluationAppMvc)getApplication())
                    .getApplicationComponent()
                    .newActivityComponent(new ActivityModule(this));
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setLayoutDirection();
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.layout_activity_base);
        frameBaseMain = findViewById(R.id.frameBaseMain);

    }

    private ViewGroup frameBaseMain;

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, frameBaseMain, false);
        frameBaseMain.addView(view);
    }

    @Override
    public void setContentView(View view) {

        frameBaseMain.addView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setLayoutDirection() {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    }

    @UiThread
    protected ControllerComponent getControllerComponent() {
        if (isControllerComponentUsed) {
            throw new IllegalStateException("must not use ControllerComponent more than once");
        }
        isControllerComponentUsed = true;
        return getActivityComponent()
                .newControllerComponent(new ControllerModule(), new ViewMvcModule());
    }

    public abstract ActivityName getActivityName();
}
