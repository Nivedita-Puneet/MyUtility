package com.nivedita.weatherutility.di.component;

import com.nivedita.weatherutility.PreferenceFragment;
import com.nivedita.weatherutility.SettingsActivity;
import com.nivedita.weatherutility.WeatherReportActivity;
import com.nivedita.weatherutility.di.module.ActivityModule;
import com.nivedita.weatherutility.di.module.SettingsActivityModule;
import com.nivedita.weatherutility.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface WeatherActvityComponent {

    void inject(WeatherReportActivity weatherReportActivity);
}
