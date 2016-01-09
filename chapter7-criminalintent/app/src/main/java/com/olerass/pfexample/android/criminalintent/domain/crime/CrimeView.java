package com.olerass.pfexample.android.criminalintent.domain.crime;

import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

public interface CrimeView {
    void whenTitleChanged(DataEventListener<String> listener);
}
