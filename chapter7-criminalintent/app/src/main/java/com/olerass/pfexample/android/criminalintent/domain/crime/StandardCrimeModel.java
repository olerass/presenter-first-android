package com.olerass.pfexample.android.criminalintent.domain.crime;

public class StandardCrimeModel implements CrimeModel {
    private Crime crime = new Crime();

    @Override
    public void setTitle(String title) {
        crime.setTitle(title);
    }
}
