package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public interface QuestionView {
    void setQuestionText(String question);
    void showCorrectAnswerMessage();
    void showIncorrectAnswerMessage();
    void showCheaterMessage();

    void whenAnswerGiven(DataEventListener<Boolean> listener);
    void whenNextQuestionRequested(EventListener listener);
    void whenCheatRequested(EventListener listener);
}
