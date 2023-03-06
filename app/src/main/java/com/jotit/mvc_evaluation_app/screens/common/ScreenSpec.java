package com.jotit.mvc_evaluation_app.screens.common;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is a base class for all screen specs in our app (navigation payloads)
 */
public abstract class ScreenSpec implements Serializable {

    private final ScreenName screenName;
    private final ActivityName enclosingActivityName;

    public ScreenSpec(ScreenName screenName, ActivityName enclosingActivityName) {
        this.screenName = screenName;
        this.enclosingActivityName = enclosingActivityName;
    }

    public ScreenName getScreenName() {
        return screenName;
    }

    public ActivityName getEnclosingActivityName() {
        return enclosingActivityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScreenSpec)) return false;
        ScreenSpec that = (ScreenSpec) o;
        return screenName == that.screenName && enclosingActivityName == that.enclosingActivityName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(screenName, enclosingActivityName);
    }

    @NonNull
    @Override
    public String toString() {
        return "[activity name: "  + enclosingActivityName + "; screen name: " + screenName + "]";
    }
}
