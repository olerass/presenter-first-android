package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import java.util.List;
import java.util.UUID;

public interface CrimeListView {
    void setCrimes(List<Crime> crimes);
    void showCrimeMessage(String crimeTitle);

    void whenCrimeSelected(DataEventListener<UUID> listener);
}
