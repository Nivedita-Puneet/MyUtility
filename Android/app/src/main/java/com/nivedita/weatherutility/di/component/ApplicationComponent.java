package com.nivedita.weatherutility.di.component;


import android.app.Application;
import android.content.Context;

import com.nivedita.weatherutility.di.module.ApplicationModule;
import com.nivedita.weatherutility.di.module.NetworkModule;
import com.nivedita.weatherutility.di.module.SettingsActivityModule;
import com.nivedita.weatherutility.di.scope.ApplicationContext;
import com.nivedita.weatherutility.model.datalayer.DataManager;
import com.nivedita.weatherutility.model.local.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, SettingsActivityModule.class})
public interface ApplicationComponent {

    Application application();

    DataManager dataManager();

    SharedPrefsHelper getPreferenceHelper();

    @ApplicationContext
    Context context();
}
