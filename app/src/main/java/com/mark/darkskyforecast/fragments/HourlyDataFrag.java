package com.mark.darkskyforecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.model.DailyData;
import com.mark.darkskyforecast.model.Forecast;

/**
 * Created by mark on 12/25/15.
 * this fragment handles the hourly data
 */
public class HourlyDataFrag  extends Fragment implements Forecast.ForecastObserver{
    private TextView mTemp;
    private TextView mHumidity;
    private TextView mWindSpeed;
    private TextView mVisibility;
    private TextView mPressure;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Forecast.addObserver(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.houly_data_layout, container, false);
        setupWidgets(rootView);
        return rootView;
    }


    private void setupWidgets(View v){
        mTemp = (TextView)v.findViewById(R.id.tvHourlyTemp);
        mHumidity = (TextView)v.findViewById(R.id.tvHumidity);
        mWindSpeed = (TextView)v.findViewById(R.id.tvVisibility);
        mPressure = (TextView)v.findViewById(R.id.tvPressure);
        mVisibility = (TextView)v.findViewById(R.id.tvVisibility);

    }

    @Override
    public void setForecastResult() {
        DailyData today  = Forecast.getInstance().getTodayForecast();
        mTemp.setText("Temperature " + today.getTemperature());
        mHumidity.setText("Humidity "+ today.getHumidity());
        mWindSpeed.setText("Wind speed " + today.getWindSpeed());
        mVisibility.setText("Visibility " + today.getVisibility());
        mPressure.setText("Pressure " + today.getPressure());

    }
}
