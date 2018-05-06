package com.nivedita.weatherutility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.WeatherReportAdapterViewHolder> {

    private String[] mWeatherData;
    private Context context;
    private WeatherReportAdapterOnclickHandler weatherReportAdapterOnclickHandler;

    public WeatherReportAdapter(Context context,
                                WeatherReportAdapterOnclickHandler weatherReportAdapterOnclickHandler) {

        this.context = context;
        this.weatherReportAdapterOnclickHandler = weatherReportAdapterOnclickHandler;
        mWeatherData = new String[20];

    }

    public interface WeatherReportAdapterOnclickHandler {

        void clickListener(String weatherForToday);
    }

    public class WeatherReportAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView mWeatherTextView;

        WeatherReportAdapterViewHolder(View view) {
            super(view);
            mWeatherTextView = (TextView) view.findViewById(R.id.tv_weather_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            WeatherReportAdapter.this.weatherReportAdapterOnclickHandler.
                    clickListener(mWeatherData[getAdapterPosition()]);
        }
    }

    @NonNull
    @Override
    public WeatherReportAdapter.WeatherReportAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int id = R.layout.weather_report_item;
        boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(context).inflate(id, parent, shouldAttachToParentImmediately);
        return new WeatherReportAdapterViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull WeatherReportAdapter.WeatherReportAdapterViewHolder holder, int position) {

        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        } else {

            return mWeatherData.length;
        }

    }

    public void setmWeatherData(String[] mWeatherData) {

        Log.i(WeatherReportAdapter.class.getSimpleName(), ""+ mWeatherData.length);
        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }
}
