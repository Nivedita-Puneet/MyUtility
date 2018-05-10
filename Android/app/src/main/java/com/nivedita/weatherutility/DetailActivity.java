package com.nivedita.weatherutility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nivedita.weatherutility.model.Network.ConstantsUtil;

/**
 * Created by PUNEETU on 06-05-2018.
 */

public class DetailActivity extends BaseActivity {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeControls();
    }

    private void initializeControls() {

        textView = findViewById(R.id.getWeather);
        textView.setText(getIntent().getExtras().getString(ConstantsUtil.GETWEATHERFORTODAY));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {

            case R.id.action_settings:
                startActivity(new Intent(DetailActivity.this,
                        SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
