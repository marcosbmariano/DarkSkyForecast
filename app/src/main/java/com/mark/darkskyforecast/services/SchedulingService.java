package com.mark.darkskyforecast.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.mark.darkskyforecast.model.Forecast;

/**
 * Created by mark on 1/4/16.
 */
public class SchedulingService extends IntentService{

    public SchedulingService(){
        super("SchedulingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Forecast.getInstance().updateForecast();
    }

}
