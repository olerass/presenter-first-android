package com.olerass.pfexample.android.geoquiz.domain.cheat;

import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;
import com.olerass.pfexample.android.geoquiz.domain.state.StateLoader;
import com.olerass.pfexample.android.geoquiz.domain.state.StateSaver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StandardCheatModelTest {
    @Test
    public void getCorrectAnswer_returnsTheAnswerGivenInConstructor_whenCalled() {
        StandardCheatModel model = new StandardCheatModel("True");

        String res = model.getCorrectAnswer();

        assertEquals("True", res);
    }

    @Test
    public void isAnswerShown_returnsFalseByDefault() {
        StandardCheatModel model = new StandardCheatModel("True");

        boolean res = model.isAnswerShown();

        assertEquals(false, res);
    }

    @Test
    public void setAnswerShown_notifiesListenerThatAnswerShownChanged_whenCalled() {
        StandardCheatModel model = new StandardCheatModel("True");
        EventListener changeListener = mock(EventListener.class);
        model.whenAnswerShownChanged(changeListener);

        model.setAnswerShown(true);

        verify(changeListener).onDispatch();
    }

    @Test
    public void saveState_savesAnswerShown_whenCalled() {
        StandardCheatModel model = new StandardCheatModel("True");
        model.setAnswerShown(false);
        StateSaver stateSaver = mock(StateSaver.class);

        model.saveState(stateSaver);

        verify(stateSaver).saveBool("answerShown", false);
    }

    @Test
    public void loadState_loadsAnswerShown_whenCalled() {
        StandardCheatModel model = new StandardCheatModel("True");
        StateLoader stateLoader = mock(StateLoader.class);
        when(stateLoader.loadBool("answerShown", false)).thenReturn(true);

        model.loadState(stateLoader);
        boolean answerShown = model.isAnswerShown();

        assertEquals(true, answerShown);
    }

    @Test
    public void loadState_notifiesListenerThatAnswerShownChanged_whenLoadedValueIsDifferentFromCurrent() {
        StandardCheatModel model = new StandardCheatModel("True");
        model.setAnswerShown(false);
        EventListener listener = mock(EventListener.class);
        model.whenAnswerShownChanged(listener);
        StateLoader stateLoader = mock(StateLoader.class);
        when(stateLoader.loadBool("answerShown", false)).thenReturn(true);

        model.loadState(stateLoader);

        verify(listener).onDispatch();
    }

    @Test
    public void loadState_doesNotNotifyListenerThatAnswerShownChanged_whenLoadedValueIsSameAsCurrent() {
        StandardCheatModel model = new StandardCheatModel("True");
        model.setAnswerShown(true);
        EventListener listener = mock(EventListener.class);
        model.whenAnswerShownChanged(listener);
        StateLoader stateLoader = mock(StateLoader.class);
        when(stateLoader.loadBool("answerShown", false)).thenReturn(true);

        model.loadState(stateLoader);

        verify(listener, never()).onDispatch();
    }
}
