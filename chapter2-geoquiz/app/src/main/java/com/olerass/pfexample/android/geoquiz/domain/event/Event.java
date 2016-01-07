package com.olerass.pfexample.android.geoquiz.domain.event;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public void dispatch() {
        for (EventListener listener : listeners) {
            listener.onDispatch();
        }
    }
}
