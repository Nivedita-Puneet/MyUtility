package com.nivedita.weatherutility;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.nivedita.weatherutility.base.WeatherUtilityApplication;
import com.nivedita.weatherutility.di.component.DaggerSettingsFragmentComponent;
import com.nivedita.weatherutility.di.component.SettingsFragmentComponent;
import com.nivedita.weatherutility.di.module.SettingsActivityModule;
import com.nivedita.weatherutility.model.datalayer.DataManager;

import javax.inject.Inject;

/**
 * Created by PUNEETU on 08-05-2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    DataManager dataManager;

    private SettingsFragmentComponent mActivityComponent;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.pref_general);
        initializeDagger();

        SharedPreferences sharedPreferences = dataManager.getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        int count = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p = preferenceScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }


    }

    private void initializeDagger() {

        getmActivityComponent().inject(SettingsFragment.this);
    }

    private SettingsFragmentComponent getmActivityComponent() {
        if (mActivityComponent == null) {

            mActivityComponent = DaggerSettingsFragmentComponent.builder().
                    settingsActivityModule(new SettingsActivityModule(getActivity())).
                    applicationComponent(WeatherUtilityApplication.get(getActivity()).getApplicationComponent()).build();

        }

        return mActivityComponent;
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        /* Unregister the preference change listener */
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // COMPLETED (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
    @Override
    public void onStart() {
        super.onStart();
        /* Register the preference change listener */
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);

    }
}