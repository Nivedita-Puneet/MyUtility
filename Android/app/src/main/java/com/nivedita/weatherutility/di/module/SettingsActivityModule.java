package com.nivedita.weatherutility.di.module;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceScreen;

import com.nivedita.weatherutility.di.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PUNEETU on 09-05-2018.
 */

@Module
public class SettingsActivityModule {

    private Activity mActivity;


    public SettingsActivityModule(Activity mActivity) {
        this.mActivity = mActivity;

    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }


}
