package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StandardCrimeListModelTest {
    @Test
    public void getCrimes_returnsTheCrimesStoredInCrimeLab_whenCalled() {
        CrimeLab lab = mock(CrimeLab.class);
        List<Crime> crimes = new ArrayList<>();
        crimes.add(new Crime());
        when(lab.getCrimes()).thenReturn(crimes);
        StandardCrimeListModel model = new StandardCrimeListModel(lab);

        List<Crime> res = model.getCrimes();
        assertThat(res, is(equalTo(crimes)));
    }

    @Test
    public void getCrime_returnsCrimeWithGivenUUIDFromCrimeLab_whenItExists() {
        CrimeLab lab = mock(CrimeLab.class);
        Crime targetCrime = new Crime();
        targetCrime.setTitle("TargetTitle");
        when(lab.getCrime(targetCrime.getId())).thenReturn(targetCrime);
        StandardCrimeListModel model = new StandardCrimeListModel(lab);

        Crime res = model.getCrime(targetCrime.getId());
        assertThat(res, is(equalTo(targetCrime)));
    }
}
