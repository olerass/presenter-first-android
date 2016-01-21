package com.olerass.pfexample.android.criminalintent.domain.crime;

import com.olerass.pfexample.android.criminalintent.domain.crimes.CrimeLab;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class CrimeLabTest {
    private CrimeLab lab;

    @Before
    public void setup() {
        lab = CrimeLab.get();
    }

    @Test
    public void get_returnsSingletonInstance_whenCalled() {
        CrimeLab lab2 = CrimeLab.get();

        assertThat(lab2, is(sameInstance(lab)));
    }

    @Test
    public void constructor_initializesCrimeLabWith100BoringCrimes_whenCalled() {
        List<Crime> crimes = lab.getCrimes();

        assertThat(crimes, hasSize(100));
        assertThat(crimes.get(0).getTitle(), is("Crime #0"));
    }

    @Test
    public void getCrime_returnsCrimeWithGivenUUID_whenItExists() {
        Crime targetCrime = lab.getCrimes().get(0);

        Crime foundCrime = lab.getCrime(targetCrime.getId());

        assertThat(foundCrime, is(targetCrime));
    }
}
