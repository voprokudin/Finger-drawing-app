package com.jotit.mvc_evaluation_app.common.dependencyinjection.controller;

import com.jotit.mvc_evaluation_app.screens.drawing.DrawingFragment;
import com.jotit.mvc_evaluation_app.screens.greetings.GreetingsFragment;
import com.jotit.mvc_evaluation_app.screens.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ControllerModule.class, ViewMvcModule.class})
public interface ControllerComponent {

    void inject(MainActivity mainActivity);

    void inject(GreetingsFragment fragment);

    void inject(DrawingFragment fragment);
}
