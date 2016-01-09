package com.olerass.pfexample.android.criminalintent.platform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olerass.pfexample.android.criminalintent.domain.crime.CrimeModel;
import com.olerass.pfexample.android.criminalintent.domain.crime.CrimePresenter;
import com.olerass.pfexample.android.criminalintent.domain.crime.StandardCrimeModel;

public class CrimeFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CrimeModel model = new StandardCrimeModel();
        FragmentCrimeView fragment = new FragmentCrimeView(inflater, container);
        new CrimePresenter(fragment, model);

        return fragment.getView();
    }
}

