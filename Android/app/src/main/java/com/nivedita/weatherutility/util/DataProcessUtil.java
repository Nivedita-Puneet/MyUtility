package com.nivedita.weatherutility.util;

import android.content.Context;
import android.util.Log;

import com.nivedita.weatherutility.model.List;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by PUNEETU on 04-05-2018.
 */

public class DataProcessUtil {

    private static String TAG = DataProcessUtil.class.getSimpleName();

    public static String[] getWeatherUtils(ArrayList<List> weatherArray,
                                           Context context, String description, double high, double low) {

        String[] weatherUtils = new String[weatherArray.size()];

        long localDate = System.currentTimeMillis();
        long utcDate = WeatherDateUtils.getUTCDateFromLocal(localDate);
        long startDay = WeatherDateUtils.normalizeDate(utcDate);

        long dateTimeMillis;

        int arraySize = weatherArray.size();
        for (int i = 0; i < arraySize; i++) {

            String date;
            String highAndLow;

            dateTimeMillis = startDay + WeatherDateUtils.DAY_IN_MILLIS * i;
            date = WeatherDateUtils.getFriendlyDateString(context,
                    dateTimeMillis, false);
            Log.i(TAG, date);


            highAndLow = DataProcessUtil.formatHighLows(context, high, low);

            weatherUtils[i] = date + "-" + description + "-" + highAndLow;
            Log.i(TAG + "The weather details are", weatherUtils[i]);
        }

        return weatherUtils;
    }

    public static String formatHighLows(Context context, double high, double low) {

        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }

}
