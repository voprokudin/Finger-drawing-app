package com.jotit.mvc_evaluation_app.screens;

import android.app.Activity;
import android.os.Bundle;

import com.jotit.mvc_evaluation_app.screens.common.ActivityName;
import com.jotit.mvc_evaluation_app.screens.common.BaseActivity;
import com.jotit.mvc_evaluation_app.screens.common.DummyRootFragment;
import com.jotit.mvc_evaluation_app.screens.common.ScreenSpec;
import com.jotit.mvc_evaluation_app.screens.drawing.DrawingFragment;
import com.jotit.mvc_evaluation_app.screens.greetings.GreetingsFragment;
import com.jotit.mvc_evaluation_app.screens.main.MainActivity;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;

import androidx.fragment.app.Fragment;

import java.util.Collections;

import javax.inject.Inject;

public class ScreensNavigator {

    private final FragNavController fragNavController;
    private final Activity activity;

    public ScreensNavigator(Activity activity, FragNavController fragNavController) {
        this.activity = activity;
        this.fragNavController = fragNavController;
    }

    public void init(Bundle savedInstanceState, ScreenSpec screenSpec) {
        fragNavController.setRootFragments(Collections.singletonList(new DummyRootFragment()));
        fragNavController.setCreateEager(true);
        fragNavController.setDefaultTransactionOptions(new FragNavTransactionOptions.Builder().build());
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
        toScreen(screenSpec);
    }

    public void toScreen(ScreenSpec screenSpec) {
        ActivityName targetActivityName = screenSpec.getEnclosingActivityName();
        if (getCurrentActivityName() != targetActivityName) {
            switch (screenSpec.getEnclosingActivityName()) {
                case MAIN:
                    MainActivity.start(activity, screenSpec);
                    break;
                default:
                    throw new RuntimeException("unsupported activity: " + screenSpec.getEnclosingActivityName());
            }
        } else {
            Fragment fragment = createFragmentForScreenSpec(screenSpec);

            changeFragmentTo(fragment);
        }
    }

    private Fragment createFragmentForScreenSpec(ScreenSpec screenSpec) {
        Fragment fragment;
        switch (screenSpec.getScreenName()) {
            case GREETINGS:
                fragment = GreetingsFragment.newInstance();
                break;
            case DRAWING:
                fragment = DrawingFragment.newInstance();
                break;
            default:
                throw new IllegalArgumentException("unsupported screen: " + screenSpec);
        }
        return fragment;
    }

    private void changeFragmentTo(Fragment fragment) {
        fragNavController.pushFragment(fragment);
    }

    public boolean navigateBack() {
        if (fragNavController.isRootFragment()) {
            return false;
        } else {
            fragNavController.popFragment();
            return true;
        }
    }

    private ActivityName getCurrentActivityName() {
        return ((BaseActivity) activity).getActivityName();
    }

}
