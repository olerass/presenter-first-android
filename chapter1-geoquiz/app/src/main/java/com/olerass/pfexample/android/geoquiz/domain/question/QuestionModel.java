package com.olerass.pfexample.android.geoquiz.domain.question;

public interface QuestionModel {
    String getQuestionText();
    boolean isCorrectAnswer(boolean answer);
}
