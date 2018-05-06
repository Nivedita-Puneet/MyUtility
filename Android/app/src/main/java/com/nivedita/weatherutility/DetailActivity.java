package com.nivedita.weatherutility;

import android.os.Bundle;
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
}
