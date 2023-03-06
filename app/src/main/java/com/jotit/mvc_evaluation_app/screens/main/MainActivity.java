package com.jotit.mvc_evaluation_app.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jotit.mvc_evaluation_app.R;
import com.jotit.mvc_evaluation_app.screens.ScreensNavigator;
import com.jotit.mvc_evaluation_app.screens.common.ActivityName;
import com.jotit.mvc_evaluation_app.screens.common.BaseActivity;
import com.jotit.mvc_evaluation_app.screens.common.ScreenSpec;
import com.jotit.mvc_evaluation_app.screens.greetings.GreetingsScreenSpec;
import java.io.Serializable;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject ScreensNavigator screensNavigator;
    public static Context contextOfApplication;

    public static void start(Context context, ScreenSpec screenSpec) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(INTENT_EXTRA_SCREEN, (Serializable) screenSpec);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getControllerComponent().inject(this);
        setContentView(R.layout.layout_single_frame);

        contextOfApplication = getApplicationContext();
        ScreenSpec targetScreenSpec = new GreetingsScreenSpec();
        screensNavigator.init(savedInstanceState, targetScreenSpec);
    }

    @Override
    public ActivityName getActivityName() {
        return ActivityName.MAIN;
    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    public void onBackPressed() {
        if (!screensNavigator.navigateBack()) {
            super.onBackPressed();
        }
    }
}
