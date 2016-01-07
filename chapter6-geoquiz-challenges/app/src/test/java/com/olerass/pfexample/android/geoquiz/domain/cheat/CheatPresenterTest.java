package com.olerass.pfexample.android.geoquiz.domain.cheat;

import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheatPresenterTest {
    @Test
    public void tellsViewToDisplayCorrectAnswerFromModel_whenViewRequestsIt() {
        CheatView view = mock(CheatView.class);
        CheatModel model = mock(CheatModel.class);
        when(model.getCorrectAnswer()).thenReturn("True");
        new CheatPresenter(view, model);

        ArgumentCaptor<EventListener> argument = ArgumentCaptor.forClass(EventListener.class);
        verify(view).whenShowAnswerRequested(argument.capture());
        argument.getValue().onDispatch();

        verify(view).setAnswer("True");
    }

    @Test
    public void tellsModelThatAnswerWasShown_whenViewRequestsItToBe() {
        CheatView view = mock(CheatView.class);
        CheatModel model = mock(CheatModel.class);
        new CheatPresenter(view, model);

        ArgumentCaptor<EventListener> argument = ArgumentCaptor.forClass(EventListener.class);
        verify(view).whenShowAnswerRequested(argument.capture());
        argument.getValue().onDispatch();

        verify(model).setAnswerShown(true);
    }
}
