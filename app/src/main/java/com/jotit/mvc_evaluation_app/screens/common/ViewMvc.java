package com.jotit.mvc_evaluation_app.screens.common;

import android.view.View;


/**
 * MVC view is a "dumb" component used for presenting information to the user and capturing user's input.
 */
public interface ViewMvc {

    /**
     * @return the root Android View which is used internally by this MVC View.
     */
    View getRootView();

}
