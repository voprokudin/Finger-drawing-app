package com.jotit.mvc_evaluation_app.screens.drawing;

import com.jotit.mvc_evaluation_app.screens.common.BaseObservableViewMvc;

public abstract class DrawingViewMvc extends BaseObservableViewMvc<DrawingViewMvc.Listener> {

    interface Listener {
        void onTextRecognized(String text);
        void onTextRecognitionError();
    }
}
