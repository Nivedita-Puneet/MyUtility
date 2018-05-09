package com.nivedita.weatherutility.di.module;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.nivedita.weatherutility.di.scope.ApplicationContext;
import com.nivedita.weatherutility.model.Network.ConstantsUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application provideApplication(){

        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs(){
        return mApplication.getSharedPreferences(ConstantsUtil.WEATHERSHAREDPREFS, Context.MODE_PRIVATE);
    }
}
