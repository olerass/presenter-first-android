package com.olerass.pfexample.android.geoquiz.domain.state;

public interface StateSaver {
    void saveInt(String key, int value);
    void saveBool(String key, boolean value);
}