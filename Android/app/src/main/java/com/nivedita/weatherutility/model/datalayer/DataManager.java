package com.nivedita.weatherutility.model.datalayer;

import com.nivedita.weatherutility.model.Network.ConstantsUtil;
import com.nivedita.weatherutility.model.Network.WeatherService;
import com.nivedita.weatherutility.model.WeatherReport;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class DataManager {

    private final WeatherService weatherService;
    private static final String TAG = DataManager.class.getSimpleName();

    @Inject
    public DataManager(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Flowable<WeatherReport> getDailyWeatherReport() {


        return weatherService.getWeatherReport(ConstantsUtil.PLACE,
                Integer.toString(ConstantsUtil.COUNT),
                ConstantsUtil.UNITS,
                ConstantsUtil.FORMAT_PARAM,
                ConstantsUtil.WEATHER_API_KEY);
    }
}
