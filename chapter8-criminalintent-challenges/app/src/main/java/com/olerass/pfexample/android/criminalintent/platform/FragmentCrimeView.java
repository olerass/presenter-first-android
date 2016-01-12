package com.olerass.pfexample.android.criminalintent.platform;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.olerass.pfexample.android.criminalintent.R;
import com.olerass.pfexample.android.criminalintent.domain.crime.CrimeView;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEvent;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import java.util.Date;

public class FragmentCrimeView implements CrimeView {
    private View view;
    private EditText titleField;
    private Button dateButton;
    private CheckBox solvedCheckBox;
    private DataEvent<String> titleChanged = new DataEvent<>();
    private DataEvent<Boolean> solvedChanged = new DataEvent<>();

    public FragmentCrimeView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_crime, container, false);

        initializeTitleField();
        initializeSolvedCheckBox();
        initializeDateButton();
    }

    public View getView() {
        return view;
    }

    @Override
    public void setDate(Date date) {
        CharSequence formattedDate = DateFormat.format("EEEE, MMM d, yyyy", date);
        dateButton.setText(formattedDate);
    }

    @Override
    public void disableDateInteraction() {
        dateButton.setEnabled(false);
    }

    @Override
    public void whenTitleChanged(DataEventListener<String> listener) {
        titleChanged.addListener(listener);
    }

    @Override
    public void whenSolvedChanged(DataEventListener<Boolean> listener) {
        solvedChanged.addListener(listener);
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

    private void initializeSolvedCheckBox() {
        solvedCheckBox = (CheckBox)view.findViewById(R.id.crime_solved);
        solvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> solvedChanged.dispatch(isChecked));
    }

    private void initializeDateButton() {
        dateButton = (Button)view.findViewById(R.id.crime_date);
    }
}