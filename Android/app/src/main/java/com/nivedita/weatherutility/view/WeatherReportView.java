package com.nivedita.weatherutility.view;

import com.nivedita.weatherutility.model.Network.LogNetworkError;
import com.nivedita.weatherutility.model.WeatherReport;

public interface WeatherReportView extends MVPView {


        void showWeatherReports(WeatherReport weatherReport);

        void noWeatherReports();

        void showError(LogNetworkError logNetworkError);

        void showWait();

        void removeWait();

}
