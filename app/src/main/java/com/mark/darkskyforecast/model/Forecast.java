package com.mark.darkskyforecast.model;


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
    private static Set<ForecastObserver> mObserver = new HashSet<>();

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
        warnObservers();
    }
    //warn observers when there is a change in the forecast data
    private void warnObservers(){
        Iterator<ForecastObserver> iterator = mObserver.iterator();
        while( iterator.hasNext()){
            iterator.next().setForecastResult();
        }        
    }

    public List<DailyData> getNextDaysForecast(){
        return nextDaysForecast;
    }
    //return false if updateForecast fails
    public boolean updateForecast(){
        if( mToday == null){
            return false;
        }
        return mToday.updateHourlyData();
    }

    public DailyData getTodayForecast(){
        return mToday;
    }

    public static void addObserver(ForecastObserver observer){
        mObserver.add(observer);
    }

    public static void removeObserver(){
        //todo check this
    }

    public interface ForecastObserver{
        void setForecastResult();
    }

}



