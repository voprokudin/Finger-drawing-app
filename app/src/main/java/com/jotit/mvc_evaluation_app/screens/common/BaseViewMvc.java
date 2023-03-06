package com.jotit.mvc_evaluation_app.screens.common;

import android.view.View;

import com.jotit.mvc_evaluation_app.common.BaseObservable;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;

public abstract class BaseViewMvc {

    private View mRootView;

    protected void setRootView(View rootView) {
        mRootView = rootView;
    }

    public View getRootView() {
        return mRootView;
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return getRootView().findViewById(id);
    }

    protected int getColor(@ColorRes int colorId) {
        return mRootView.getContext().getResources().getColor(colorId);
    }
}
