package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public interface QuestionModel {
    String getQuestionText();
    boolean isCorrectAnswer(boolean answer);
    void gotoNextQuestion();

    void whenQuestionTextChanged(EventListener listener);
}
