package com.olerass.pfexample.android.criminalintent.platform.crimes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olerass.pfexample.android.criminalintent.domain.crimes.CrimeLab;
import com.olerass.pfexample.android.criminalintent.domain.crimes.CrimeListModel;
import com.olerass.pfexample.android.criminalintent.domain.crimes.CrimeListPresenter;
import com.olerass.pfexample.android.criminalintent.domain.crimes.StandardCrimeListModel;

public class CrimeListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CrimeListModel model = new StandardCrimeListModel(CrimeLab.get());
        FragmentCrimeListView fragment = new FragmentCrimeListView(inflater, container);
        new CrimeListPresenter(fragment, model);

        return fragment.getView();
    }
}
