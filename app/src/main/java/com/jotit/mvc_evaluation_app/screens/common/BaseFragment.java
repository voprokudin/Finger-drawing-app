package com.jotit.mvc_evaluation_app.screens.common;

import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ControllerComponent;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ControllerModule;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ViewMvcModule;

public abstract class BaseFragment extends Fragment {
    private boolean isControllerComponentUsed = false;


    @UiThread
    protected ControllerComponent getControllerComponent() {
        if (isControllerComponentUsed) {
            throw new IllegalStateException("must not use ControllerComponent more than once");
        }
        isControllerComponentUsed = true;
        return ((BaseActivity)getActivity())
                .getActivityComponent()
                .newControllerComponent(new ControllerModule(), new ViewMvcModule());
    }

    public abstract ScreenName getScreenName();

}
