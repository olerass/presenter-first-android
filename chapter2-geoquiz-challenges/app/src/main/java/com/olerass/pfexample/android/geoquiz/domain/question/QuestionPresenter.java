package com.olerass.pfexample.android.geoquiz.domain.question;

public class QuestionPresenter {
    private QuestionView view;
    private QuestionModel model;

    public QuestionPresenter(QuestionView view, QuestionModel model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    private void initialize() {
        syncViewToModel();

        view.whenAnswerGiven(answer -> {
            if (model.isCorrectAnswer(answer)) {
                view.showCorrectAnswerMessage();
            } else {
                view.showIncorrectAnswerMessage();
            }
        });

        view.whenNextQuestionRequested(model::gotoNextQuestion);
        view.whenPreviousQuestionRequested(model::gotoPreviousQuestion);
        model.whenQuestionTextChanged(this::syncViewToModel);
    }

    private void syncViewToModel() {
        view.setQuestionText(model.getQuestionText());
    }
}
