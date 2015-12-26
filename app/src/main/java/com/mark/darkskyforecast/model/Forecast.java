package com.mark.darkskyforecast.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by mark on 12/21/15.
 */
public class Forecast {

    private List<DailyData> nextDaysForecast = new ArrayList<>();
    private static DailyData mToday;
    private static Set<ForecastObserver> mObserver = new HashSet<>();
    //Change to ArrayMap

    private Forecast(){}


    public static Forecast getInstance(){
        return Holder.mInstance;
    }

    public static class Holder{ //for concurrency safety
        private static Forecast mInstance = new Forecast();
    }

    public void setNextDaysForecast(List<DailyData> nextDaysForecast){
        mToday = nextDaysForecast.remove(0);
        this.nextDaysForecast = nextDaysForecast;
        warnObservers();
    }

    private void warnObservers(){
        Iterator<ForecastObserver> iterator = mObserver.iterator();
        while( iterator.hasNext()){
            iterator.next().setForecastResult();
        }        
    }

    public List<DailyData> getNextDaysForecast(){
        return nextDaysForecast;
    }

    //get the time for the last hourly data available
    public long getTimeToUpdate(){
        return mToday.getLastHourlyDataTime();
    }



    public void updateCurrentHourlyData(){
        //mToday.getCurrentHourlyData()


    }
    private boolean isTimeToUpdateHourlyData(){
        return false;
    }

    private void updateForecast(){

    }

    private boolean isTimeToUpdateForecast(){
        return false;
    }


    public DailyData getTodayForecast(){
        return mToday;
    }

    public static void addObserver(ForecastObserver observer){
        mObserver.add(observer);
    }

    public static void removeObserver(){

    }


    public interface ForecastObserver{
        void setForecastResult();
    }

//    private String getForecast(){
//        StringBuilder bd = new StringBuilder();
//        bd.append("\n "+ "Time " + mToday.getTime());
//        bd.append("\n " + "Summary " + mToday.getSummary());
//        bd.append("\n " + "Icon  " + mToday.getIcon());
//        bd.append("\n " + "SunsetTime  " + mToday.getSunsetTime());
//        bd.append("\n " + "Precip Intensity " + mToday.getPrecipIntensity());
//        bd.append("\n " + "PrecipProbability " + mToday.getPrecipProbability());
//        bd.append("\n " + "Temperature " + mToday.getTemperature());
//        bd.append("\n " + "ApparentTemperature " + mToday.getApparentTemperature());
//        bd.append("\n " + "DewPoint " + mToday.getDewPoint());
//        bd.append("\n " + "Humidity " + mToday.getHumidity());
//        bd.append("\n " + "tWindSpeed " + mToday.getWindSpeed());
//        bd.append("\n " + "WindBearing " + mToday.getWindBearing());
//        bd.append("\n " + "Visibility " + mToday.getVisibility());
//        bd.append("\n " + "CloudCover " + mToday.getCloudCover());
//        bd.append("\n " + "Pressure " + mToday.getPressure());
//        bd.append("\n " + "Ozone " + mToday.getOzone());
//        bd.append("\n " + "SunriseTime " + mToday.getSunriseTime());
//        bd.append("\n " + "MoonPhase " + mToday.getMoonPhase());
//        bd.append("\n " + "PrecipIntensityMax " + mToday.getPrecipIntensityMax());
//        bd.append("\n " + "PrecipIntensityMaxTime " + mToday.getPrecipIntensityMaxTime());
//        bd.append("\n " + "PrecipType " + mToday.getPrecipType());
//        bd.append("\n " + "TemperatureMin " + mToday.getTemperatureMin());
//        bd.append("\n " + "TemperatureMinTime " + mToday.getTemperatureMinTime());
//        bd.append("\n " + "TemperatureMax " + mToday.getTemperatureMax());
//        bd.append("\n " + "TemperatureMaxTime " + mToday.getTemperatureMaxTime());
//        bd.append("\n " + "ApparentTemperatureMin " + mToday.getApparentTemperatureMin());
//        bd.append("\n " + "ApparentTemperatureMinTime " + mToday.getApparentTemperatureMinTime());
//        bd.append("\n " + "ApparentTemperatureMax " + mToday.getApparentTemperatureMax());
//        bd.append("\n "+ "ApparentTemperatureMaxTime " +mToday.getApparentTemperatureMaxTime());
//
//        return bd.toString();
//    }


}
