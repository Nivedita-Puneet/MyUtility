package com.nivedita.weatherutility.base;

import android.app.Application;
import android.content.Context;

import com.nivedita.weatherutility.di.component.ApplicationComponent;
import com.nivedita.weatherutility.di.component.DaggerApplicationComponent;
import com.nivedita.weatherutility.di.module.ApplicationModule;
import com.nivedita.weatherutility.di.module.NetworkModule;

/**
 * Created by PUNEETU on 02-05-2018.
 */

public class WeatherUtilityApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static WeatherUtilityApplication get(Context context) {

        return (WeatherUtilityApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {

        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule()).build();
        }

        return applicationComponent;
    }
}
