package com.olerass.pfexample.android.geoquiz.domain.state;

import android.os.Bundle;

public class AndroidStateManager implements StateSaver, StateLoader {
    private Bundle bundle;

    public AndroidStateManager(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void saveInt(String key, int value) {
        bundle.putInt(key, value);
    }

    @Override
    public int loadInt(String key, int defaultIfNotFound) {
        if (bundle == null) {
            return defaultIfNotFound;
        }
        return bundle.getInt(key, defaultIfNotFound);
    }

    @Override
    public void saveBool(String key, boolean value) {
        bundle.putBoolean(key, value);
    }

    @Override
    public boolean loadBool(String key, boolean defaultIfNotFound) {
        if (bundle == null) {
            return defaultIfNotFound;
        }
        return bundle.getBoolean(key, defaultIfNotFound);
    }
}
