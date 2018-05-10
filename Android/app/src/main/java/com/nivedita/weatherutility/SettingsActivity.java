package com.nivedita.weatherutility;


import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.nivedita.weatherutility.di.component.WeatherActvityComponent;

/**
 * Created by PUNEETU on 08-05-2018.
 */

public class SettingsActivity extends BaseActivity {

    private WeatherActvityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {
            //NavUtils.navigateUpFromSameTask(this);
            onBackPressed();
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
