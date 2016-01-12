package com.olerass.pfexample.android.criminalintent.domain.crime;

public class CrimePresenter {
    private CrimeView view;
    private CrimeModel model;

    public CrimePresenter(CrimeView view, CrimeModel model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    private void initialize() {
        view.setDate(model.getDate());
        view.disableDateInteraction();

        view.whenTitleChanged(model::setTitle);
        view.whenSolvedChanged(model::setSolved);
    }
}
