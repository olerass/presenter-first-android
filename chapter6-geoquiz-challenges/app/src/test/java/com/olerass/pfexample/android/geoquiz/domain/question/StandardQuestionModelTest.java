package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;
import com.olerass.pfexample.android.geoquiz.domain.state.StateLoader;
import com.olerass.pfexample.android.geoquiz.domain.state.StateSaver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StandardQuestionModelTest {
    @Test
    public void constructor_initializesCurrentQuestionToFirstQuestionGivenInConstructor_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true),
                new Question("question2", false)
        });

        assertEquals("question1", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void constructor_initializesCheaterToFalse_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{});

        boolean res = model.isCheater();

        assertEquals(false, res);
    }

    @Test
    public void saveState_savesTheCurrentQuestionIndex_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true),
        });

        StateSaver stateSaver = mock(StateSaver.class);
        model.saveState(stateSaver);

        verify(stateSaver).saveInt("index", 0);
    }

    @Test
    public void loadState_setsCurrentQuestionToQuestionAtSavedIndex_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true),
                new Question("question2", false)
        });

        StateLoader stateLoader = mock(StateLoader.class);
        when(stateLoader.loadInt("index", 0)).thenReturn(1);
        model.loadState(stateLoader);

        assertEquals("question2", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(false));
    }

    @Test
    public void gotoNextQuestion_setsCurrentQuestionToTheOneFollowingTheCurrentQuestion_whenThereIsOneFollowing() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true),
                new Question("question2", false)
        });

        model.gotoNextQuestion();

        assertEquals("question2", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(false));
    }

    @Test
    public void gotoNextQuestion_setsCurrentQuestionToFirstQuestion_whenCurrentQuestionIsLastOne() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true)
        });

        model.gotoNextQuestion();

        assertEquals("question1", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void gotoNextQuestion_notifiesListenerThatQuestionTextHasChanged_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true)
        });
        EventListener textChangedListener = mock(EventListener.class);
        model.whenQuestionTextChanged(textChangedListener);

        model.gotoNextQuestion();

        verify(textChangedListener).onDispatch();
    }

    @Test
    public void gotoNextQuestion_resetsIsCheaterToFalse_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true)
        });

        model.setCheater(true);
        model.gotoNextQuestion();
        boolean isCheater = model.isCheater();

        assertEquals(false, isCheater);
    }

    @Test
    public void cheatRequested_notifiesListenerThatCheatWasRequested_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true)
        });
        DataEventListener<Boolean> cheatListener = mock(DataEventListener.class);
        model.whenCheatRequested(cheatListener);

        model.cheatRequested();

        verify(cheatListener).onDispatch(true);
    }

    @Test
    public void setCheater_changesValueOfIsCheaterToTheOneGiven_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[]{
                new Question("question1", true)
        });

        model.setCheater(true);

        assertEquals(true, model.isCheater());
    }
}
