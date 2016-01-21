package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;

import java.util.List;
import java.util.UUID;

public class StandardCrimeListModel implements CrimeListModel {
    private CrimeLab lab;

    public StandardCrimeListModel(CrimeLab lab) {
        this.lab = lab;
    }

    @Override
    public List<Crime> getCrimes() {
        return lab.getCrimes();
    }

    @Override
    public Crime getCrime(UUID uuid) {
        return lab.getCrime(uuid);
    }
}
