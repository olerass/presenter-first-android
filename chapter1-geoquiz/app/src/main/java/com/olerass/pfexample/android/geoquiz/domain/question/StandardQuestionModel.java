package com.olerass.pfexample.android.geoquiz.domain.question;

public class StandardQuestionModel implements QuestionModel {
    private Question question;

    public StandardQuestionModel(Question question) {
        this.question = question;
    }

    @Override
    public String getQuestionText() {
        return question.getText();
    }

    @Override
    public boolean isCorrectAnswer(boolean answer) {
        return answer == question.getCorrectAnswer();
    }
}
