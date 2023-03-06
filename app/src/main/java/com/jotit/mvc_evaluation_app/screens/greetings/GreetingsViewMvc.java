package com.jotit.mvc_evaluation_app.screens.greetings;

import com.jotit.mvc_evaluation_app.screens.common.BaseObservableViewMvc;


public abstract class GreetingsViewMvc extends BaseObservableViewMvc<GreetingsViewMvc.Listener> {

    interface Listener {
        void onStartClicked();
    }
}
