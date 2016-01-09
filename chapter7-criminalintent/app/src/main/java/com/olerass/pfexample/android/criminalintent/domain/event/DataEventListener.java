package com.olerass.pfexample.android.criminalintent.domain.event;

public interface DataEventListener<T> {
    void onDispatch(T data);
}
