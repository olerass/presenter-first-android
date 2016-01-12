package com.olerass.pfexample.android.criminalintent.domain.crime;

public class StandardCrimeModel implements CrimeModel {
    private Crime crime;

    public StandardCrimeModel(Crime crime) {
        this.crime = crime;
    }

    @Override
    public void setTitle(String title) {
        crime.setTitle(title);
    }
}
