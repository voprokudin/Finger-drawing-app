package com.jotit.mvc_evaluation_app.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.jotit.mvc_evaluation_app.screens.drawing.DrawingViewMvc;
import com.jotit.mvc_evaluation_app.screens.drawing.DrawingViewMvcImpl;
import com.jotit.mvc_evaluation_app.screens.greetings.GreetingsViewMvcImpl;
import com.jotit.mvc_evaluation_app.screens.greetings.GreetingsViewMvc;
import androidx.annotation.Nullable;
import javax.inject.Inject;

public class ViewMvcFactory {

    private final LayoutInflater layoutInflater;

    @Inject
    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public GreetingsViewMvc newGreetingsViewMvc(@Nullable ViewGroup parent) {
        return new GreetingsViewMvcImpl(layoutInflater, parent, this);
    }

    public DrawingViewMvc newDrawingViewMvc(@Nullable ViewGroup parent) {
        return new DrawingViewMvcImpl(layoutInflater, parent, this);
    }
}
