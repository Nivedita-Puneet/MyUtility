package com.nivedita.weatherutility.presenter;

import android.content.SharedPreferences;

import com.nivedita.weatherutility.model.Network.LogNetworkError;
import com.nivedita.weatherutility.model.WeatherReport;
import com.nivedita.weatherutility.model.datalayer.DataManager;
import com.nivedita.weatherutility.view.MainMVPView;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeatherReportPresenter extends BasePresenter<MainMVPView> implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private final DataManager mDataManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public WeatherReportPresenter(DataManager mDataManager) {

        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(MainMVPView mainMVPView) {
        super.attachView(mainMVPView);

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(getWeatherReports());
    }

    private Disposable getWeatherReports() {

        return sendRequestToApiObservable().subscribe(new Consumer<WeatherReport>() {
            @Override
            public void accept(WeatherReport weatherReport) throws Exception {
                if (!weatherReport.getList().isEmpty()) {
                    getMvpView().showWeatherReports(weatherReport);

                } else {
                    getMvpView().noWeatherReports();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                getMvpView().showError(new LogNetworkError(throwable));
            }
        });

    }

    private Flowable<WeatherReport> sendRequestToApiObservable() {

        return mDataManager.getDailyWeatherReport(mDataManager.getDefaultLocation()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void detachView() {
        super.detachView();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        //TODO: reload the servive with new location.
        //TODO: change the preferences and update the location.
         // step1: The WeatherReporter Activity gets called when location changes so implement changes in Presenter.
         // Step2 : The shared preference change listener will identify that value is changed in shared preferences and hence refreshes the activity.
        // Step 3: Register and un register the listener in attach and detach view.
    }
}
