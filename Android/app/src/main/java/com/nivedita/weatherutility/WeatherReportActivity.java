package com.nivedita.weatherutility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.nivedita.weatherutility.model.List;
import com.nivedita.weatherutility.model.Network.LogNetworkError;
import com.nivedita.weatherutility.model.WeatherReport;
import com.nivedita.weatherutility.model.Weatherattrs;
import com.nivedita.weatherutility.presenter.WeatherReportPresenter;
import com.nivedita.weatherutility.view.MainMVPView;

import java.util.ArrayList;

import javax.inject.Inject;

public class WeatherReportActivity extends BaseActivity implements MainMVPView {

    @Inject
    WeatherReportPresenter weatherReportPresenter;

    @Inject
    WeatherReportAdapter weatherReportAdapter;
    /*Initialize views*/
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;

    String[] weatherArray;

    private static String TAG = WeatherReportActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);
        initializeControls();
    }

    private void initializeControls() {

        /*Initialize the dagger component*/

        getmActivityComponent().inject(WeatherReportActivity.this);

        /*Attach the view to the presenter*/
        weatherReportPresenter.attachView(WeatherReportActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(weatherReportAdapter);


    }

    @Override
    public void showWeatherReports(WeatherReport weatherReport) {

        //TODO: Need to refactor Data processing
        ArrayList<Weatherattrs> weatherDetails = new ArrayList<>();
        int arraySize = weatherReport.getList().size();
        for (int i = 0; i < arraySize; i++) {

            /*get the parameters to be displayed.*/
            long date = weatherReport.getList().get(i).getDt();
            double min = weatherReport.getList().get(i).getTemp().getMin();
            double max = weatherReport.getList().get(i).getTemp().getMax();

            String desc = weatherReport.getList().get(i).getWeather().get(0).getMain();

            Weatherattrs weatherattrs = new Weatherattrs(date, min, max, desc);
            weatherDetails.add(weatherattrs);

            Log.i(TAG,
                    weatherDetails.get(i).getDate() + "-" +
                            weatherDetails.get(i).getWeatherDesc());
        }

    }

    @Override
    public void noWeatherReports() {

    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

        Log.e(TAG, logNetworkError.getAppErrorMessage());
    }

    @Override
    public void showWait() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        weatherReportPresenter.detachView();
    }
}
