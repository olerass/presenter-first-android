package com.olerass.pfexample.android.criminalintent.domain.crime;

import java.util.Date;

public interface CrimeModel {
    Date getDate();
    void setTitle(String title);
    void setSolved(boolean solved);
}
