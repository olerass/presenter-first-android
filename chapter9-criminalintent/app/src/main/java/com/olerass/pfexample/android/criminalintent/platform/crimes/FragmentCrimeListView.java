package com.olerass.pfexample.android.criminalintent.platform.crimes;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.olerass.pfexample.android.criminalintent.R;
import com.olerass.pfexample.android.criminalintent.domain.crime.Crime;
import com.olerass.pfexample.android.criminalintent.domain.crimes.CrimeListView;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEvent;
import com.olerass.pfexample.android.criminalintent.domain.event.DataEventListener;

import java.util.List;
import java.util.UUID;

public class FragmentCrimeListView implements CrimeListView {
    private View view;
    private RecyclerView crimeRecyclerView;
    private DataEvent<UUID> crimeSelectedEvent = new DataEvent<>();

    public FragmentCrimeListView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        crimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(null));
    }

    public View getView() {
        return view;
    }

    @Override
    public void setCrimes(List<Crime> crimes) {
        CrimeAdapter adapter = new CrimeAdapter(crimes);
        crimeRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showCrimeMessage(String crimeTitle) {
        Toast.makeText(view.getContext(), crimeTitle + " clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void whenCrimeSelected(DataEventListener<UUID> listener) {
        crimeSelectedEvent.addListener(listener);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
        private Crime crime;
        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox solvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> crimeSelectedEvent.dispatch(crime.getId()));

            titleTextView = (TextView)itemView.findViewById(R.id.list_item_crime_title_text_view);
            dateTextView = (TextView)itemView.findViewById(R.id.list_item_crime_date_text_view);
            solvedCheckBox = (CheckBox)itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            this.crime = crime;
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDate().toString());
            solvedCheckBox.setChecked(crime.isSolved());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = crimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }
}