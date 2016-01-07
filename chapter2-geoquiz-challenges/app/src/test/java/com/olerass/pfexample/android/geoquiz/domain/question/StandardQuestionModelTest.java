package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StandardQuestionModelTest {
    @Test
    public void constructor_initializesTheCurrentQuestionToFirstQuestionGivenInConstructor() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", true),
                new Question("question2", false)
        });
        assertEquals("question1", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void gotoNextQuestion_setsCurrentQuestionToTheOneFollowingTheCurrentQuestion_whenThereIsOneFollowing() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", true),
                new Question("question2", false)
        });

        model.gotoNextQuestion();

        assertEquals("question2", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(false));
    }

    @Test
    public void gotoNextQuestion_setsCurrentQuestionToFirstQuestion_whenCurrentQuestionIsLastOne() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", true)
        });

        model.gotoNextQuestion();

        assertEquals("question1", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void gotoNextQuestion_notifiesListenerThatQuestionTextHasChanged_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", true)
        });
        EventListener textChangedListener = mock(EventListener.class);
        model.whenQuestionTextChanged(textChangedListener);

        model.gotoNextQuestion();

        verify(textChangedListener).onDispatch();
    }

    @Test
    public void gotoPreviousQuestion_setsCurrentQuestionToTheOneBeforeTheCurrentQuestion_whenThereIsOneBefore() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", true),
                new Question("question2", false)
        });

        model.gotoNextQuestion();
        assertEquals("question2", model.getQuestionText());
        model.gotoPreviousQuestion();

        assertEquals("question1", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void gotoPreviousQuestion_setsCurrentQuestionToLastQuestion_whenCurrentQuestionIsFirstOne() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", false),
                new Question("question2", false),
                new Question("question3", true)
        });

        model.gotoPreviousQuestion();

        assertEquals("question3", model.getQuestionText());
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void gotoPreviousQuestion_notifiesListenerThatQuestionTextHasChanged_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question[] {
                new Question("question1", true),
                new Question("question2", false),
        });
        EventListener textChangedListener = mock(EventListener.class);
        model.whenQuestionTextChanged(textChangedListener);

        model.gotoPreviousQuestion();

        verify(textChangedListener).onDispatch();
    }
}
