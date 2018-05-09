package com.nivedita.weatherutility;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.nivedita.weatherutility.base.WeatherUtilityApplication;
import com.nivedita.weatherutility.di.component.DaggerSettingsFragmentComponent;
import com.nivedita.weatherutility.di.component.DaggerWeatherActvityComponent;
import com.nivedita.weatherutility.di.component.SettingsFragmentComponent;
import com.nivedita.weatherutility.di.component.WeatherActvityComponent;
import com.nivedita.weatherutility.di.module.SettingsActivityModule;
import com.nivedita.weatherutility.model.datalayer.DataManager;

import javax.inject.Inject;

/**
 * Created by PUNEETU on 08-05-2018.
 */

public class PreferenceFragment extends PreferenceFragmentCompat {

    @Inject
    DataManager dataManager;

    private SettingsFragmentComponent mActivityComponent;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.pref_general);
        initializeDagger();
    }

    private void initializeDagger() {

        getmActivityComponent().inject(PreferenceFragment.this);
    }

    private SettingsFragmentComponent getmActivityComponent() {
        if (mActivityComponent == null) {

            mActivityComponent = DaggerSettingsFragmentComponent.builder().
                    settingsActivityModule(new SettingsActivityModule(getActivity())).
                    applicationComponent(WeatherUtilityApplication.get(getActivity()).getApplicationComponent()).build();

        }

        return mActivityComponent;
    }

    private void provideSharedPrefs(){
        dataManager.getDefaultLocation();
    }
}
