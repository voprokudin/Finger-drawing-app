package com.jotit.mvc_evaluation_app.screens.greetings;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import com.jotit.mvc_evaluation_app.R;
import com.jotit.mvc_evaluation_app.screens.common.ViewMvcFactory;

public class GreetingsViewMvcImpl extends GreetingsViewMvc {

    private final Button btnStart;

    public GreetingsViewMvcImpl(LayoutInflater inflater, ViewGroup container, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_greetings, container, false));
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            for (GreetingsViewMvc.Listener listener : getListeners()) {
                listener.onStartClicked();
            }
        });
    }
}
