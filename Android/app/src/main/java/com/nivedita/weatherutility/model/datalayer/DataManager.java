package com.nivedita.weatherutility.model.datalayer;

import android.content.SharedPreferences;

import com.nivedita.weatherutility.model.Network.ConstantsUtil;
import com.nivedita.weatherutility.model.Network.WeatherService;
import com.nivedita.weatherutility.model.WeatherReport;
import com.nivedita.weatherutility.model.local.SharedPrefsHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class DataManager {

    private final WeatherService weatherService;
    private static final String TAG = DataManager.class.getSimpleName();
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(WeatherService weatherService, SharedPrefsHelper mSharedPrefsHelper) {
        this.weatherService = weatherService;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
    }

    public Flowable<WeatherReport> getDailyWeatherReport(String place) {


        return weatherService.getWeatherReport(place,
                Integer.toString(ConstantsUtil.COUNT),
                ConstantsUtil.UNITS,
                ConstantsUtil.FORMAT_PARAM,
                ConstantsUtil.WEATHER_API_KEY);

    }

    public void storeLocationDetails(String location) {
        mSharedPrefsHelper.put(ConstantsUtil.LOCATION_KEY, location);
    }

    public String getDefaultLocation() {

        return mSharedPrefsHelper.get(ConstantsUtil.LOCATION_KEY, ConstantsUtil.DEFAULT_LOCATION);
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPrefsHelper.getSharedPreferences();
    }


}
