package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionPresenterTest {
    private QuestionModel model;
    private QuestionView view;

    @Test
    public void initializesViewWithQuestionFromModel_whenConstructed() {
        model = mock(QuestionModel.class);
        when(model.getQuestionText()).thenReturn("the question");
        view = mock(QuestionView.class);
        new QuestionPresenter(view, model);

        verify(view).setQuestionText("the question");
    }

    @Test
    public void tellsViewToShowCorrectAnswerMessage_WhenUserAnswersTrueAndItsCorrect() {
        makePresenterWithModelAnswer(true, true);
        simulateUserAnswer(true);
        verify(view).showCorrectAnswerMessage();
    }

    @Test
    public void tellsViewToShowIncorrectAnswerMessage_WhenUserAnswersTrueAndItsIncorrect() {
        makePresenterWithModelAnswer(false, false);
        simulateUserAnswer(true);
        verify(view).showIncorrectAnswerMessage();
    }

    @Test
    public void tellsViewToShowCorrectAnswerMessage_WhenUserAnswersFalseAndItsCorrect() {
        makePresenterWithModelAnswer(false, true);
        simulateUserAnswer(false);
        verify(view).showCorrectAnswerMessage();
    }

    @Test
    public void tellsViewToShowIncorrectAnswerMessage_WhenUserAnswersFalseAndItsIncorrect() {
        makePresenterWithModelAnswer(true, false);
        simulateUserAnswer(false);
        verify(view).showIncorrectAnswerMessage();
    }

    @Test
    public void tellsModelToGotoNextQuestion_whenViewRequestsIt() {
        model = mock(QuestionModel.class);
        view = mock(QuestionView.class);
        new QuestionPresenter(view, model);

        ArgumentCaptor<EventListener> argument = ArgumentCaptor.forClass(EventListener.class);
        verify(view).whenNextQuestionRequested(argument.capture());
        argument.getValue().onDispatch();

        verify(model).gotoNextQuestion();
    }

    @Test
    public void updatesViewWithNewQuestionText_whenModelChanges() {
        model = mock(QuestionModel.class);
        when(model.getQuestionText()).thenReturn("question1", "question2");
        view = mock(QuestionView.class);
        new QuestionPresenter(view, model);

        ArgumentCaptor<EventListener> argument = ArgumentCaptor.forClass(EventListener.class);
        verify(model).whenQuestionTextChanged(argument.capture());
        argument.getValue().onDispatch();

        verify(view).setQuestionText("question2");
    }

    private void makePresenterWithModelAnswer(boolean answer, boolean isCorrectAnswer) {
        model = mock(QuestionModel.class);
        when(model.isCorrectAnswer(answer)).thenReturn(isCorrectAnswer);
        view = mock(QuestionView.class);
        new QuestionPresenter(view, model);
    }

    private void simulateUserAnswer(boolean answer) {
        ArgumentCaptor<DataEventListener<Boolean>> argument = ArgumentCaptor.forClass((Class)DataEventListener.class);
        verify(view).whenAnswerGiven(argument.capture());
        argument.getValue().onDispatch(answer);
    }
}