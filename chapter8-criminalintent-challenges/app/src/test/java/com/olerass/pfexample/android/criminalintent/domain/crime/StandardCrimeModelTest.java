package com.olerass.pfexample.android.criminalintent.domain.crime;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StandardCrimeModelTest {
    @Test
    public void getData_returnsTheDateFromTheBackingCrime_whenCalled() {
        Crime crime = new Crime();
        StandardCrimeModel model = new StandardCrimeModel(crime);

        assertThat(model.getDate(), is(equalTo(crime.getDate())));
    }

    @Test
    public void setTitle_setsTheTitleInBackingCrime_whenCalled() {
        Crime crime = new Crime();
        StandardCrimeModel model = new StandardCrimeModel(crime);

        model.setTitle("title");
        assertThat(crime.getTitle(), is(equalTo("title")));
    }

    @Test
    public void setSolved_setsTheSolvedPropertyInBackingCrime_whenCalled() {
        Crime crime = new Crime();
        StandardCrimeModel model = new StandardCrimeModel(crime);

        model.setSolved(true);
        assertThat(crime.isSolved(), is(true));
    }
}
