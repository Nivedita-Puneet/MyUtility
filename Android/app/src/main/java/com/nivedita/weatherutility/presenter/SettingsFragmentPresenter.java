package com.nivedita.weatherutility.presenter;

import android.content.SharedPreferences;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;


import com.nivedita.weatherutility.model.datalayer.DataManager;
import com.nivedita.weatherutility.view.MVPView;

import javax.inject.Inject;

/**
 * Created by PUNEETU on 10-05-2018.
 */

public class SettingsFragmentPresenter extends BasePresenter<MVPView> implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    DataManager mDataManager;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();
        String key = preference.getKey();

        if (preference instanceof ListPreference) {
            /* For list preferences, look up the correct display value in */
            /* the preference's 'entries' list (since they have separate labels/values). */
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
    }

}
