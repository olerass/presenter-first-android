package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab instance;
    private List<Crime> crimes;

    public static CrimeLab get() {
        if (instance == null) {
            instance = new CrimeLab();
        }
        return instance;
    }

    private CrimeLab() {
        crimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            crimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
