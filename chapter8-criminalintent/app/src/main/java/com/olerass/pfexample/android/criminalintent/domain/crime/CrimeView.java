package com.olerass.pfexample.android.criminalintent.domain.crime;

import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import java.util.Date;

public interface CrimeView {
    void setDate(Date date);
    void disableDateInteraction();

    void whenTitleChanged(DataEventListener<String> listener);
    void whenSolvedChanged(DataEventListener<Boolean> listener);
}
