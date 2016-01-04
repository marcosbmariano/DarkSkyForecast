package com.mark.darkskyforecast.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.helpers.DataHandler;
import com.mark.darkskyforecast.model.DailyData;
import com.mark.darkskyforecast.model.Forecast;

/**
 * Created by mark on 12/21/15.
 */
public class MainForecastFrag extends Fragment {
    private TextView mSummary;
    private TextView mMinMax;
    private TextView mDate;
    private MyReceiver mReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupReceiver();
    }

    private void setupReceiver(){
        mReceiver = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext())
                .registerReceiver(mReceiver, new IntentFilter(DataHandler.NEW_FORECAST_DOWNLOADED));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v =
                (ViewGroup)inflater.inflate(R.layout.main_forecast_frag_layout, container, false);
        setupViews(v);
        return v;
    }

    @Override
    public void onDestroy() {
        if( null != mReceiver){
            LocalBroadcastManager.getInstance(getContext())
                    .unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        super.onDestroy();
    }

    private void setupViews(View v){
        mDate = (TextView)v.findViewById(R.id.tvDate);
        mSummary = (TextView)v.findViewById(R.id.tvSummary);
        mMinMax = (TextView)v.findViewById(R.id.tvMinMax);
    }

    public void setupForecastData() {
        DailyData todayForecast = Forecast.getInstance().getTodayForecast();
        mDate.setText(todayForecast.getStringDate());
        mSummary.setText( todayForecast.getSummary());
        mMinMax.setText( "" + todayForecast.getTemperatureMin() + "/" + todayForecast.getTemperatureMax());

    }

    private class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if( intent.getAction().equals(DataHandler.NEW_FORECAST_DOWNLOADED)){
                setupForecastData();
                Log.d("MainForecastFrag", "onReceive");
            }
        }
    }


}
