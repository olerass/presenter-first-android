package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;

import java.util.List;
import java.util.UUID;

public interface CrimeListModel {
    List<Crime> getCrimes();
    Crime getCrime(UUID uuid);
}
