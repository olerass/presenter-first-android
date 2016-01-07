package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;

public interface QuestionView {
    void setQuestionText(String question);
    void showCorrectAnswerMessage();
    void showIncorrectAnswerMessage();

    void whenAnswerGiven(DataEventListener<Boolean> listener);
}
