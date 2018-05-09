package com.nivedita.weatherutility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nivedita.weatherutility.model.List;
import com.nivedita.weatherutility.model.Network.LogNetworkError;
import com.nivedita.weatherutility.model.WeatherReport;
import com.nivedita.weatherutility.model.Weatherattrs;
import com.nivedita.weatherutility.presenter.WeatherReportPresenter;
import com.nivedita.weatherutility.util.DataProcessUtil;
import com.nivedita.weatherutility.util.WeatherDateUtils;
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
        weatherArray = new String[weatherReport.getList().size()];

        long localDate = System.currentTimeMillis();
        long utcDate = WeatherDateUtils.getUTCDateFromLocal(localDate);
        long startDay = WeatherDateUtils.normalizeDate(utcDate);

        long dateTimeMillis;
        double high;
        double low;
        String description;

        int arraySize = weatherReport.getList().size();
        for (int i = 0; i < arraySize; i++) {

            String date;
            String highAndLow;

            dateTimeMillis = startDay + WeatherDateUtils.DAY_IN_MILLIS * i;
            date = WeatherDateUtils.getFriendlyDateString(WeatherReportActivity.this,
                    dateTimeMillis, false);
            Log.i(TAG, date);

            /*Get the description paramater*/
            description = weatherReport.getList().get(i).getWeather().get(0).getMain();

            /*get the low and max temprature.*/
            low = weatherReport.getList().get(i).getTemp().getMin();
            high = weatherReport.getList().get(i).getTemp().getMax();
            highAndLow = DataProcessUtil.formatHighLows(WeatherReportActivity.this,
                    high, low);

            weatherArray[i] = date + "-" + description + "-" + highAndLow;
            Log.i(TAG + "The weather details are", weatherArray[i]);

        }

        //Set the adapter to a weather array.
        weatherReportAdapter.setmWeatherData(weatherArray);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(WeatherReportActivity.this,
                    SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void noWeatherReports() {

        Log.i(TAG, "No weather reports to dislay");
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
