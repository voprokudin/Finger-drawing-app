package com.jotit.mvc_evaluation_app.screens.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableViewMvc<LISTENER_CLASS> extends BaseViewMvc {

    private Set<LISTENER_CLASS> listeners = new HashSet<>();

    public final void registerListener(LISTENER_CLASS listener) {
        listeners.add(listener);
    }

    public final void unregisterListener(LISTENER_CLASS listener) {
        listeners.remove(listener);
    }

    protected final Set<LISTENER_CLASS> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }
}
