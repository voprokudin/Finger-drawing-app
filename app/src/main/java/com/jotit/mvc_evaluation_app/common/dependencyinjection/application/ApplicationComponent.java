package com.jotit.mvc_evaluation_app.common.dependencyinjection.application;

import com.jotit.mvc_evaluation_app.EvaluationAppMvc;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.activity.ActivityComponent;
import com.jotit.mvc_evaluation_app.common.dependencyinjection.activity.ActivityModule;

import dagger.Component;
@ApplicationScope
@Component (modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ActivityComponent newActivityComponent(ActivityModule activityModule);
    void inject(EvaluationAppMvc evaluationAppMvc);

}



