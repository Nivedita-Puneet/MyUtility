package com.nivedita.weatherutility.di.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.nivedita.weatherutility.DetailActivity;
import com.nivedita.weatherutility.WeatherReportAdapter;
import com.nivedita.weatherutility.di.scope.ActivityContext;
import com.nivedita.weatherutility.model.Network.ConstantsUtil;
import com.nivedita.weatherutility.model.WeatherReport;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;
    private WeatherReportAdapter.WeatherReportAdapterOnclickHandler weatherReportAdapterOnclickHandler;

    public ActivityModule(Activity mActivity,
                          WeatherReportAdapter.WeatherReportAdapterOnclickHandler weatherReportAdapterOnclickHandler) {
        this.mActivity = mActivity;
        this.weatherReportAdapterOnclickHandler = weatherReportAdapterOnclickHandler;
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

    @Provides
    WeatherReportAdapter weatherReportAdapter() {
        return new WeatherReportAdapter(this.mActivity,this.weatherReportAdapterOnclickHandler);
    }

    /*@Provides
    @ActivityContext
    WeatherReportAdapter.WeatherReportAdapterOnclickHandler weatherReportAdapterOnclickHandler() {
        return new WeatherReportAdapter.WeatherReportAdapterOnclickHandler() {
            @Override
            public void clickListener(String weatherForToday) {

                Intent intent = provideDetailIntent();
                Log.i(ActivityModule.class.getSimpleName(), ""+intent);
                intent.putExtra(ConstantsUtil.GETWEATHERFORTODAY, weatherForToday);
                ContextCompat.startActivity(mActivity,intent,null);
            }
        };
    }

    @Provides
    Intent provideDetailIntent(){

        return new Intent(mActivity, DetailActivity.class);
    }*/

}
