package com.jotit.mvc_evaluation_app.screens.greetings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jotit.mvc_evaluation_app.screens.ScreensNavigator;
import com.jotit.mvc_evaluation_app.screens.common.BaseFragment;
import com.jotit.mvc_evaluation_app.screens.common.BaseViewMvc;
import com.jotit.mvc_evaluation_app.screens.common.ScreenName;
import com.jotit.mvc_evaluation_app.screens.common.ScreenSpec;
import com.jotit.mvc_evaluation_app.screens.common.ViewMvcFactory;
import com.jotit.mvc_evaluation_app.screens.drawing.DrawingScreenSpec;


import javax.inject.Inject;

public class GreetingsFragment extends BaseFragment
        implements GreetingsViewMvc.Listener {

    public static GreetingsFragment newInstance() {
        return new GreetingsFragment();
    }

    @Inject ViewMvcFactory viewMvcFactory;
    @Inject ScreensNavigator screensNavigator;

    private GreetingsViewMvc viewMvc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.newGreetingsViewMvc(container);
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }


    @Override
    public void onStartClicked() {
        ScreenSpec targetScreenSpec = new DrawingScreenSpec();
        screensNavigator.toScreen(targetScreenSpec);
    }

    @Override
    public ScreenName getScreenName() {
        return ScreenName.GREETINGS;
    }
}
