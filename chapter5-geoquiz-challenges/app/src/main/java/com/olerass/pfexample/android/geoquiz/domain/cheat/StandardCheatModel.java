package com.olerass.pfexample.android.geoquiz.domain.cheat;

import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;
import com.olerass.pfexample.android.geoquiz.domain.state.StateLoader;
import com.olerass.pfexample.android.geoquiz.domain.state.StateSaver;

public class StandardCheatModel implements CheatModel {
    private final String KEY_ANSWER_SHOWN = "answerShown";
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
        boolean oldValue = this.answerShown;
        this.answerShown = answerShown;

        if (oldValue != answerShown) {
            answerShownChangedEvent.dispatch();
        }
    }

    public boolean isAnswerShown() {
        return answerShown;
    }

    public void whenAnswerShownChanged(EventListener listener) {
        answerShownChangedEvent.addListener(listener);
    }

    public void saveState(StateSaver stateSaver) {
        stateSaver.saveBool(KEY_ANSWER_SHOWN, answerShown);
    }

    public void loadState(StateLoader stateLoader) {
        setAnswerShown(stateLoader.loadBool(KEY_ANSWER_SHOWN, false));
    }
}
