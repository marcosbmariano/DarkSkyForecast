package com.mark.darkskyforecast.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mark.darkskyforecast.model.Forecast;
import com.mark.darkskyforecast.model.ForecastHelper;

/**
 * Created by mark on 12/22/15.
 *
 * This service handles the request to a Forecast
 *
 *
 */
public class ForecastRequestService extends Service {
    private static CountDownTimer mTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getForecasts();
        setCountDownTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    private void getForecasts(){
        if( isTimeToRequestForecasts()){
            ForecastHelper.getInstance().getForecasts();
        }
    }
    //check if it is time to request forecast
    //the schedule will be based on the last hourly available or
    //at the turn of the current day
    private boolean isTimeToRequestForecasts(){

        return true;
    }

    private void setCountDownTimer(){
        mTimer = new CountDownTimer(60000L, 60000L) {
            Forecast forecast = Forecast.getInstance();
            ForecastHelper helper = ForecastHelper.getInstance();
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                Log.e("CountDown", "OnFinish");
                //ForecastHelper.getInstance().getForecasts();
                //setCountDownTimer();
            }
        }.start();


    }


}
