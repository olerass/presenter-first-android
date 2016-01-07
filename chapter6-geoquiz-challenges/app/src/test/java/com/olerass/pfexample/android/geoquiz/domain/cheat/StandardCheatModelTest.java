package com.olerass.pfexample.android.geoquiz.domain.cheat;

import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}
