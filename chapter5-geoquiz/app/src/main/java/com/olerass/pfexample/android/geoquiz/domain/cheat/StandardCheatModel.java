package com.olerass.pfexample.android.geoquiz.domain.cheat;

import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public class StandardCheatModel implements CheatModel {
    private String correctAnswer;
    private boolean answerShown = false;
    private Event answerShownChangedEvent = new Event();

    public StandardCheatModel(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public void setAnswerShown(boolean answerShown) {
        this.answerShown = answerShown;
        answerShownChangedEvent.dispatch();
    }

    public boolean isAnswerShown() {
        return answerShown;
    }

    public void whenAnswerShownChanged(EventListener listener) {
        answerShownChangedEvent.addListener(listener);
    }
}
