package com.olerass.pfexample.android.geoquiz.domain.state;

public interface StateLoader {
    int loadInt(String key, int defaultIfNotFound);
    boolean loadBool(String key, boolean defaultIfNotFound);
}