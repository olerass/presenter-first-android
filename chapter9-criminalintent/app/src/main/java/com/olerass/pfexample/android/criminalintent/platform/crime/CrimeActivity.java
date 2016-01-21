package com.olerass.pfexample.android.criminalintent.platform.crime;

import android.support.v4.app.Fragment;

import com.olerass.pfexample.android.criminalintent.platform.SingleFragmentActivity;

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
