package com.mark.darkskyforecast.model;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import java.util.List;
import java.util.Set;

/**
 * Created by mark on 12/21/15.
 * this is a singleton that represents the current forecast
 * it should have some kind of persistent model
 */
public class Forecast {

    private List<DailyData> nextDaysForecast = new ArrayList<>();
    private static DailyData mToday;

    private Forecast(){}

    public static Forecast getInstance(){
        return Holder.mInstance;
    }

    public static class Holder{ //for concurrency safety
        private static Forecast mInstance = new Forecast();
    }
    //set the whole week forecast
    public void setNextDaysForecast(List<DailyData> nextDaysForecast){
        mToday = nextDaysForecast.remove(0);
        this.nextDaysForecast = nextDaysForecast;
    }

    public List<DailyData> getNextDaysForecast(){
        return nextDaysForecast;
    }


    public void updateForecast(){
        if( null == mToday ){
            ForecastHelper.getInstance().getForecasts();
            Log.e("update Forecast", "New Foreacast");
        }else{
           if( !mToday.updateHourlyData()) {
               ForecastHelper.getInstance().getForecasts();
               Log.e("update Hourly ", "Failed");
           }
        }


    }

    public DailyData getTodayForecast(){
        return mToday;
    }



}



