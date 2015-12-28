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
 * every 5 minutes update the data
 *
 */
public class ForecastRequestService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateForecast();
        setCountDownTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    private void setCountDownTimer(){
        long oneHour = 3600000L;
        long fiveMinutes = 300000L;

        //check if needs to update every 5 minutes it could be less frequent
        new CountDownTimer(oneHour, fiveMinutes) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateForecast();
            }

            @Override
            public void onFinish() {
                setCountDownTimer();
            }
        }.start();
    }

    private static void updateForecast(){
        //try to update hourly data for current day
        if( !Forecast.getInstance().updateForecast()){
            //if fails, update the whole forecast
            ForecastHelper.getInstance().getForecasts();
            Log.e("updateForecast", "Get new Forecast");
        }
    }
}
