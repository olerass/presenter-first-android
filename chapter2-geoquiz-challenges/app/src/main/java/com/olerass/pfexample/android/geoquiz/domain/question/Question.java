package com.olerass.pfexample.android.geoquiz.domain.question;

public class Question {
    private String text;
    private boolean correctAnswer;

    public Question(String text, boolean correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }
}
