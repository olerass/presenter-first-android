package com.olerass.pfexample.android.criminalintent.domain.event;

import java.util.ArrayList;
import java.util.List;

public class DataEvent<T> {
    private List<DataEventListener<T>> listeners = new ArrayList<>();

    public void addListener(DataEventListener<T> listener) {
        listeners.add(listener);
    }

    public void dispatch(T data) {
        for (DataEventListener<T> listener : listeners) {
            listener.onDispatch(data);
        }
    }
}
