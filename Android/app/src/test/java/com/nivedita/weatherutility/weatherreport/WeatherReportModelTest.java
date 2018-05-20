package com.nivedita.weatherutility.weatherreport;

import com.nivedita.weatherutility.model.Network.WeatherService;
import com.nivedita.weatherutility.model.WeatherReport;
import com.nivedita.weatherutility.model.datalayer.DataManager;
import com.nivedita.weatherutility.model.local.SharedPrefsHelper;
import com.nivedita.weatherutility.presenter.WeatherReportPresenter;
import com.nivedita.weatherutility.view.WeatherReportView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import io.reactivex.Flowable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WeatherReportModelTest {

    @Mock
    private WeatherReportView weatherReportView;

    @Mock
    private WeatherService weatherService;

    @Mock
    private SharedPrefsHelper sharedPrefsHelper;

    @Mock
    private DataManager mDataManager;

    @Mock
    WeatherReportPresenter weatherReportPresenter;

    // Set up your weather service to test it initially.
    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataToSource() throws Exception {

        WeatherReport weatherReport = new WeatherReport();

        when(weatherService.getWeatherReport("", "", "", "", "")).
                thenReturn(Flowable.just(weatherReport));

        mDataManager = new DataManager(weatherService, sharedPrefsHelper);
        weatherReportPresenter = new WeatherReportPresenter(mDataManager);

        weatherReportPresenter.attachView(weatherReportView);
        InOrder inOrder = Mockito.inOrder(weatherReportView);
        inOrder.verify(weatherReportView, times(1)).showWait();
        inOrder.verify(weatherReportView, times(1)).removeWait();
        inOrder.verify(weatherReportView, times(1)).showWeatherReports(weatherReport);
    }

    //Test your mock objects.
    @Test
    public void fetchValidDataIntoView() throws Exception {


    }
}
