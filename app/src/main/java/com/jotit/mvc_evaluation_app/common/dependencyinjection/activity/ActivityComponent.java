package com.jotit.mvc_evaluation_app.common.dependencyinjection.activity;


import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ControllerComponent;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ControllerModule;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.controller.ViewMvcModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    ControllerComponent newControllerComponent(
            ControllerModule controllerModule,
            ViewMvcModule viewMvcModule
    );

}