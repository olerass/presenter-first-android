package com.olerass.pfexample.android.criminalintent.platform;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.olerass.pfexample.android.criminalintent.R;
import com.olerass.pfexample.android.criminalintent.domain.crime.CrimeView;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEvent;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

public class FragmentCrimeView implements CrimeView {
    private View view;
    private EditText titleField;
    private DataEvent<String> titleChanged = new DataEvent<>();

    public FragmentCrimeView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_crime, container, false);

        initializeTitleField();
        titleField = (EditText)view.findViewById(R.id.crime_title);
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleChanged.dispatch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public View getView() {
        return view;
    }

    @Override
    public void whenTitleChanged(DataEventListener<String> listener) {
        titleChanged.addListener(listener);
    }

    private void initializeTitleField() {
        titleField = (EditText)view.findViewById(R.id.crime_title);
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleChanged.dispatch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}