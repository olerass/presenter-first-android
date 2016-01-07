package com.olerass.pfexample.android.geoquiz.domain.question;

import com.olerass.pfexample.android.geoquiz.domain.event.DataEvent;
import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;
import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;
import com.olerass.pfexample.android.geoquiz.domain.state.StateLoader;
import com.olerass.pfexample.android.geoquiz.domain.state.StateSaver;

import java.util.HashMap;
import java.util.Map;

public class StandardQuestionModel implements QuestionModel {
    private final String KEY_INDEX = "index";
    private final Map<String, Boolean> cheatMap = new HashMap<>();
    private Question[] questions;
    private int currentIndex;
    private Event questionTextChangedEvent = new Event();
    private DataEvent<Boolean> cheatRequestedEvent = new DataEvent<>();

    public StandardQuestionModel(Question[] questions) {
        this.questions = questions;
        cheatMap.put("CHEAT_0", false);
        cheatMap.put("CHEAT_1", false);
        cheatMap.put("CHEAT_2", false);
        cheatMap.put("CHEAT_3", false);
        cheatMap.put("CHEAT_4", false);
        cheatMap.put("CHEAT_5", false);
    }

    @Override
    public String getQuestionText() {
        return questions[currentIndex].getText();
    }

    @Override
    public boolean hasCheatedOnCurrentQuestion() {
        return cheatMap.get("CHEAT_" + currentIndex);
    }

    @Override
    public boolean isCorrectAnswer(boolean answer) {
        return answer == currentQuestionAnswer();
    }

    @Override
    public void gotoNextQuestion() {
        currentIndex = (currentIndex + 1) % questions.length;
        questionTextChangedEvent.dispatch();
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

    public void setCheatedOnCurrentQuestion(boolean cheated) {
        cheatMap.put("CHEAT_" + currentIndex, cheated);
    }

    public void saveState(StateSaver stateSaver) {
        stateSaver.saveInt(KEY_INDEX, currentIndex);
        for (Map.Entry<String, Boolean> entry : cheatMap.entrySet()) {
            stateSaver.saveBool(entry.getKey(), entry.getValue());
        }
    }

    public void loadState(StateLoader stateLoader) {
        currentIndex = stateLoader.loadInt(KEY_INDEX, 0);
        for (Map.Entry<String, Boolean> entry : cheatMap.entrySet()) {
            entry.setValue(stateLoader.loadBool(entry.getKey(), false));
        }
    }

    private boolean currentQuestionAnswer() {
        return questions[currentIndex].getCorrectAnswer();
    }
}