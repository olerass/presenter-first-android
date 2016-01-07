package com.olerass.pfexample.android.geoquiz.domain.event;

public interface DataEventListener<T> {
    void onDispatch(T data);
}
