package com.olerass.pfexample.android.geoquiz.domain.cheat;

public class CheatPresenter {
    private CheatView view;
    private CheatModel model;

    public CheatPresenter(CheatView view, CheatModel model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    private void initialize() {
        view.whenShowAnswerRequested(() -> {
            view.setAnswer(model.getCorrectAnswer());
            model.setAnswerShown(true);
        });
    }
}
