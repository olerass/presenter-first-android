package com.olerass.pfexample.android.criminalintent.domain.crimes;

import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;

public class CrimeListPresenter {
    private CrimeListView view;
    private CrimeListModel model;

    public CrimeListPresenter(CrimeListView view, CrimeListModel model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    private void initialize() {
        view.setCrimes(model.getCrimes());
        view.whenCrimeSelected(uuid -> {
            Crime selected = model.getCrime(uuid);
            view.showCrimeMessage(selected.getTitle());
        });
    }
}
