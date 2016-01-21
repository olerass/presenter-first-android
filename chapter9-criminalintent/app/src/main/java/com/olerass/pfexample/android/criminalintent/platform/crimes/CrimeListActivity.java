package com.olerass.pfexample.android.criminalintent.platform.crimes;

import android.support.v4.app.Fragment;

import com.olerass.pfexample.android.criminalintent.platform.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
