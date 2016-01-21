package com.olerass.pfexample.android.criminalintent.domain.crime;

import java.util.Date;

public class StandardCrimeModel implements CrimeModel {
    private Crime crime;

    public StandardCrimeModel(Crime crime) {
        this.crime = crime;
    }

    @Override
    public Date getDate() {
        return crime.getDate();
    }

    @Override
    public void setTitle(String title) {
        crime.setTitle(title);
    }

    @Override
    public void setSolved(boolean solved) {
        crime.setSolved(solved);
    }
}
