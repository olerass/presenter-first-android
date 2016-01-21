package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrimeListPresenterTest {
    private CrimeListView view;
    private CrimeListModel model;

    @Before
    public void setup() {
        view = mock(CrimeListView.class);
        model = mock(CrimeListModel.class);
    }

    @Test
    public void initializesViewWithCrimesFromModel_whenConstructed() {
        List<Crime> crimes = new ArrayList<>();
        crimes.add(new Crime());
        when(model.getCrimes()).thenReturn(crimes);
        new CrimeListPresenter(view, model);

        verify(view).setCrimes(crimes);
    }

    @Test
    public void tellsViewToShowMessageWithTitleOfCrime_whenCrimeIsSelected() {
        UUID targetUuid = UUID.randomUUID();
        Crime targetCrime = new Crime();
        targetCrime.setTitle("TargetTitle");
        when(model.getCrime(targetUuid)).thenReturn(targetCrime);
        new CrimeListPresenter(view, model);

        ArgumentCaptor<DataEventListener<UUID>> argument = ArgumentCaptor.forClass((Class)DataEventListener.class);
        verify(view).whenCrimeSelected(argument.capture());
        argument.getValue().onDispatch(targetUuid);

        verify(view).showCrimeMessage("TargetTitle");
    }
}
