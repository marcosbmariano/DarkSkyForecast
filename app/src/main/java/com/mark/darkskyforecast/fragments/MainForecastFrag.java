package com.mark.darkskyforecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.model.DailyData;
import com.mark.darkskyforecast.model.Forecast;

/**
 * Created by mark on 12/21/15.
 */
public class MainForecastFrag extends Fragment implements Forecast.ForecastObserver{
    private TextView mTemp;
    private TextView mSummary;
    private TextView mMinMax;
    private TextView mDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Forecast.addObserver(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.main_forecast_frag_layout, container, false);
        setupViews(v);
        return v;
    }

    private void setupViews(View v){
        mDate = (TextView)v.findViewById(R.id.tvDate);
        mTemp = (TextView)v.findViewById(R.id.tvTemp);
        mSummary = (TextView)v.findViewById(R.id.tvSummary);
        mMinMax = (TextView)v.findViewById(R.id.tvMinMax);
    }

    private void startObservingToForecast(){
        Forecast.addObserver(this);
    }

    public void stopObservingForecast(){
        Forecast.removeObserver();
    }



    @Override
    public void setForecastResult() {
        Log.e("MainForecast", "setForecastResult");
        DailyData todayForecast = Forecast.getInstance().getTodayForecast();
        mDate.setText(todayForecast.getStringDate());
        //mTemp.setText( "Temp = " + todayForecast.getTemperature());
        mSummary.setText( todayForecast.getSummary());
        mMinMax.setText( "" + todayForecast.getTemperatureMin() + "/" + todayForecast.getTemperatureMax());

    }
}
