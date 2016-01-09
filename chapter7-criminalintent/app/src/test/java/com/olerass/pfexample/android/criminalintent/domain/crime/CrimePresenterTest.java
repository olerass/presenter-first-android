package com.olerass.pfexample.android.criminalintent.domain.crime;

import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CrimePresenterTest {
    @Test
    public void updatesModelWithTitleFromView_whenItChanges() {
        CrimeView view = mock(CrimeView.class);
        CrimeModel model = mock(CrimeModel.class);
        new CrimePresenter(view, model);

        ArgumentCaptor<DataEventListener<String>> argument = ArgumentCaptor.forClass((Class)DataEventListener.class);
        verify(view).whenTitleChanged(argument.capture());
        argument.getValue().onDispatch("title");

        verify(model).setTitle("title");
    }
}
