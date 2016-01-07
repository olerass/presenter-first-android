package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.DataEvent;
import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;
import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;
import com.olerass.pfexample.android.geoquiz.domain.state.StateLoader;
import com.olerass.pfexample.android.geoquiz.domain.state.StateSaver;

public class StandardQuestionModel implements QuestionModel {
    private final String KEY_INDEX = "index";
    private Question[] questions;
    private int currentIndex;
    private boolean isCheater = false;
    private Event questionTextChangedEvent = new Event();
    private DataEvent<Boolean> cheatRequestedEvent = new DataEvent<>();

    public StandardQuestionModel(Question[] questions) {
        this.questions = questions;
    }

    @Override
    public String getQuestionText() {
        return questions[currentIndex].getText();
    }

    @Override
    public boolean isCheater() {
        return isCheater;
    }

    @Override
    public boolean isCorrectAnswer(boolean answer) {
        return answer == currentQuestionAnswer();
    }

    @Override
    public void gotoNextQuestion() {
        currentIndex = (currentIndex + 1) % questions.length;
        questionTextChangedEvent.dispatch();
        isCheater = false;
    }

    @Override
    public void cheatRequested() {
        cheatRequestedEvent.dispatch(currentQuestionAnswer());
    }

    @Override
    public void whenQuestionTextChanged(EventListener listener) {
        questionTextChangedEvent.addListener(listener);
    }

    public void whenCheatRequested(DataEventListener<Boolean> listener) {
        cheatRequestedEvent.addListener(listener);
    }

    public void setCheater(boolean isCheater) {
        this.isCheater = isCheater;
    }

    public void saveState(StateSaver stateSaver) {
        stateSaver.saveInt(KEY_INDEX, currentIndex);
    }

    public void loadState(StateLoader stateLoader) {
        currentIndex = stateLoader.loadInt(KEY_INDEX, 0);
    }

    private boolean currentQuestionAnswer() {
        return questions[currentIndex].getCorrectAnswer();
    }
}
