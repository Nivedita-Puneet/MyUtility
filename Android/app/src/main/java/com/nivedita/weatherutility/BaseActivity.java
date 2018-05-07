package com.nivedita.weatherutility;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.nivedita.weatherutility.base.WeatherUtilityApplication;
import com.nivedita.weatherutility.di.component.DaggerWeatherActvityComponent;
import com.nivedita.weatherutility.di.component.WeatherActvityComponent;
import com.nivedita.weatherutility.di.module.ActivityModule;
import com.nivedita.weatherutility.model.Network.ConstantsUtil;

public class BaseActivity extends AppCompatActivity {

    private WeatherActvityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

    public WeatherActvityComponent getmActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerWeatherActvityComponent.builder()
                    .activityModule(new ActivityModule(this, new WeatherReportAdapter.WeatherReportAdapterOnclickHandler() {
                        @Override
                        public void clickListener(String weatherForToday) {

                            Intent intent = new Intent(BaseActivity.this, DetailActivity.class);
                            intent.putExtra(ConstantsUtil.GETWEATHERFORTODAY, weatherForToday);
                            startActivity(intent);
                        }
                    }))
                    .applicationComponent(WeatherUtilityApplication.get(this).getApplicationComponent()).build();
        }
        return mActivityComponent;
    }

}
