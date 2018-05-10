package com.nivedita.weatherutility.di.component;

import com.nivedita.weatherutility.SettingsFragment;
import com.nivedita.weatherutility.di.module.SettingsActivityModule;
import com.nivedita.weatherutility.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by PUNEETU on 09-05-2018.
 */
@PerActivity
@Component( dependencies = ApplicationComponent.class,modules = {SettingsActivityModule.class})
public interface SettingsFragmentComponent {

    void inject(SettingsFragment settingsActivity);
}
