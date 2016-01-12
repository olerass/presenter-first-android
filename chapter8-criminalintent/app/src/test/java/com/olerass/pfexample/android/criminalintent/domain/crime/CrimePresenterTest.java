package com.olerass.pfexample.android.criminalintent.domain.crime;

import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrimePresenterTest {
    private CrimeView view;
    private CrimeModel model;

    @Before
    public void setup() {
        view = mock(CrimeView.class);
        model = mock(CrimeModel.class);
    }

    @Test
    public void initializesViewWithDateFromModel_whenConstructed() {
        Date crimeDate = new Date();
        when(model.getDate()).thenReturn(crimeDate);
        new CrimePresenter(view, model);

        verify(view).setDate(crimeDate);
    }

    @Test
    public void initializesViewWithDisabledDateInteraction_whenConstructed() {
        new CrimePresenter(view, model);

        verify(view).disableDateInteraction();
    }

    @Test
    public void updatesModelWithTitleFromView_whenItChanges() {
        new CrimePresenter(view, model);

        ArgumentCaptor<DataEventListener<String>> argument = ArgumentCaptor.forClass((Class)DataEventListener.class);
        verify(view).whenTitleChanged(argument.capture());
        argument.getValue().onDispatch("title");

        verify(model).setTitle("title");
    }

    @Test
    public void updatesModelWithSolvedFromView_whenItChanges() {
        new CrimePresenter(view, model);

        ArgumentCaptor<DataEventListener<Boolean>> argument = ArgumentCaptor.forClass((Class)DataEventListener.class);
        verify(view).whenSolvedChanged(argument.capture());
        argument.getValue().onDispatch(true);

        verify(model).setSolved(true);
    }
}
