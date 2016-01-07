package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public class StandardQuestionModel implements QuestionModel {
    private Question[] questions;
    private int currentIndex = 0;
    private Event questionTextChangedEvent = new Event();

    public StandardQuestionModel(Question[] questions) {
        this.questions = questions;
    }

    @Override
    public String getQuestionText() {
        return questions[currentIndex].getText();
    }

    @Override
    public boolean isCorrectAnswer(boolean answer) {
        return answer == questions[currentIndex].getCorrectAnswer();
    }

    @Override
    public void gotoNextQuestion() {
        currentIndex = (currentIndex + 1) % questions.length;
        questionTextChangedEvent.dispatch();
    }

    @Override
    public void whenQuestionTextChanged(EventListener listener) {
        questionTextChangedEvent.addListener(listener);
    }
}
