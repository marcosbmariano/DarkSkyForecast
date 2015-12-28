package com.mark.darkskyforecast.model;

import android.util.Log;

import com.mark.darkskyforecast.helpers.DateHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.List;

/**
 * Created by mark on 12/21/15.
 * this class represent the forecast for one day
 * if the DailyObject represents today forecast
 * the hourly data will be the API provided hourly data
 * mHourlyData data is the all hourly data provided by today
 * current HourlyData is the current data by the hour
 * else, it will be null
 *
 * besides Hourly data, this class is immutable *
 */
public class DailyData {

    private final Deque<HourlyData> mHourlyData;
    private HourlyData currentHourlyData;
    private long sunriseTime;
    private long sunsetTime;
    private double moonPhase;
    private double precipIntensityMax;
    private double precipIntensityMaxTime;
    private String precipType;
    private double temperatureMin;
    private double temperatureMinTime;
    private double temperatureMax;
    private double temperatureMaxTime;
    private double apparentTemperatureMin;
    private double apparentTemperatureMinTime;
    private double apparentTemperatureMax;
    private long apparentTemperatureMaxTime;
    //  "nearestStormDistance":216,
    //  "nearestStormBearing":218,


    private DailyData(){
        mHourlyData = new ArrayDeque<>();
    }

    public void setHourlyData(Deque<HourlyData> hourlyData){
        mHourlyData.addAll(hourlyData);
    }

    public void setCurrentHourlyData(HourlyData data){
        currentHourlyData = data;
    }

    public Deque<HourlyData> getHourlyDataDeque() {
        return mHourlyData;
    }

    //this method is called to update current hour
    //if all the hourlydata is not from today
    //return false to signal that the whole forecast
    //must be updated
    public boolean updateHourlyData(){
        //if the current hourly data is current return true;
        if( DateHelper.isHourCurrent(getTime())) return true;

        //if the current hourly data is not current, get the current
        //hourly data
        HourlyData currentHourData =
                getCurrentHourDataFromDeque(getHourlyDataDeque());

        //if no hourly data was founded, return false
        if( currentHourData == null)  return false;

        //if the current hourly data was found, set it as the
        //current hourly data and return true;
        setCurrentHourlyData(currentHourData);
        Log.d("DailyData", "Update hourly data");
        return true;
    }

    private HourlyData getCurrentHourDataFromDeque(Deque<HourlyData> deque){
        boolean loop = true;
        HourlyData result = null;
        //pop away any hourly data older than one hour
        while ( !deque.isEmpty() && loop ){
            if(!DateHelper.isHourCurrent(deque.peek().getTime()) ){
                deque.pop();
            }else{
                result = deque.pop();
                loop = false;
            }
        }
        return result;
    }

    public HourlyData getCurrentHourData() {
        return currentHourlyData;
    }

    public String getStringDate(){
        return new SimpleDateFormat("EE MMM d").format(getDate());
    }
    //if time is null, get a new Date object
    //else, get a Date object based on the current timeStamp provided by the
    //API
    private Date getDate(){
        if ( getTime() == 0 ){
            return new Date();
        }else{
            return DateHelper.timeStampToCalendar(getTime()).getTime();
        }
    }

    public long getTime() {
        return getCurrentHourData().getTime();
    }

    public String getSummary() {
        return getCurrentHourData().getSummary();
    }

    public int getIcon() {
        return getCurrentHourData().getIcon();
    }

    public double getPrecipIntensity() {  return getCurrentHourData().getPrecipIntensity();}

    public double getPrecipProbability() { return getCurrentHourData().getPrecipProbability(); }

    public double getTemperature() {
        return getCurrentHourData().getTemperature();
    }

    public double getApparentTemperature() { return getCurrentHourData().getApparentTemperature();}

    public double getDewPoint() {
        return getCurrentHourData().getDewPoint();
    }

    public double getHumidity() {
        return getCurrentHourData().getHumidity();
    }

    public double getWindSpeed() {
        return getCurrentHourData().getWindSpeed();
    }

    public double getWindBearing() {
        return getCurrentHourData().getWindBearing();
    }

    public double getVisibility() {
        return getCurrentHourData().getVisibility();
    }

    public double getCloudCover() {
        return getCurrentHourData().getCloudCover();
    }

    public double getPressure() {
        return getCurrentHourData().getPressure();
    }

    public double getOzone() {
        return getCurrentHourData().getOzone();
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public double getMoonPhase() {
        return moonPhase;
    }

    public double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public double getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public String getPrecipType() {
        return precipType;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public long getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }



    public static final class Builder {
        private final DailyData result;
        HourlyData.Builder hourlyBuilder = new HourlyData.Builder();

        public Builder(){
            result = new DailyData();
        }

        public DailyData build(){
            result.setCurrentHourlyData(hourlyBuilder.build());
            return result;
        }

        public DailyData.Builder setTime(long time) {
            hourlyBuilder.setTime(time);
            return this;
        }

        public DailyData.Builder setSummary(String summary) {
            hourlyBuilder.setSummary(summary);
            return this;
        }

        public DailyData.Builder setIcon(int icon) {
            hourlyBuilder.setIcon(icon);
            return this;
        }

        public DailyData.Builder setSunriseTime(long sunriseTime) {
            result.sunriseTime = sunriseTime;
            return this;
        }

        public DailyData.Builder setSunsetTime(long sunsetTime) {
            result.sunsetTime = sunsetTime;
            return this;
        }

        public DailyData.Builder setMoonPhase(double moonPhase) {
            result.moonPhase = moonPhase;
            return this;
        }

        public DailyData.Builder setPrecipIntensity(double precipIntensity) {
            hourlyBuilder.setPrecipIntensity( precipIntensity);
            return this;
        }

        public DailyData.Builder setPrecipIntensityMax(double precipIntensityMax) {
            result.precipIntensityMax = precipIntensityMax;
            return this;
        }

        public DailyData.Builder setPrecipIntensityMaxTime(double precipIntensityMaxTime) {
            result.precipIntensityMaxTime = precipIntensityMaxTime;
            return this;
        }

        public DailyData.Builder setPrecipProbability(double precipProbability) {
            hourlyBuilder.setPrecipProbability(precipProbability);
            return this;
        }

        public DailyData.Builder setPrecipType(String precipType) {
            result.precipType = precipType;
            return this;
        }

        public DailyData.Builder setTemperatureMin(double temperatureMin) {
            result.temperatureMin = temperatureMin;
            return this;
        }

        public DailyData.Builder setTemperatureMinTime(double temperatureMinTime) {
            result.temperatureMinTime = temperatureMinTime;
            return this;
        }

        public DailyData.Builder setTemperatureMax(double temperatureMa) {
            result.temperatureMax = temperatureMa;
            return this;
        }

        public DailyData.Builder setTemperatureMaxTime(double temperatureMaxTime) {
            result.temperatureMaxTime = temperatureMaxTime;
            return this;
        }

        public DailyData.Builder setApparentTemperatureMin(double apparentTemperatureMin) {
            result.apparentTemperatureMin = apparentTemperatureMin;
            return this;
        }

        public DailyData.Builder setApparentTemperatureMinTime(double apparentTemperatureMinTime) {
            result.apparentTemperatureMinTime = apparentTemperatureMinTime;
            return this;
        }

        public DailyData.Builder setApparentTemperatureMax(double apparentTemperatureMax) {
            result.apparentTemperatureMax = apparentTemperatureMax;
            return this;
        }

        public DailyData.Builder setApparentTemperatureMaxTime(long apparentTemperatureMaxTime) {
            result.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
            return this;
        }

        public DailyData.Builder setDewPoint(double dewPoint) {
            hourlyBuilder.setDewPoint( dewPoint);
            return this;
        }

        public DailyData.Builder setHumidity(double humidity) {
            hourlyBuilder.setHumidity(humidity);
            return this;
        }

        public DailyData.Builder setWindSpeed(double windSpeed) {
            hourlyBuilder.setWindSpeed(windSpeed);
            return this;
        }

        public DailyData.Builder setWindBearing(double windBearing) {
            hourlyBuilder.setWindBearing(windBearing);
            return this;
        }

        public DailyData.Builder setVisibility(double visibility) {
            hourlyBuilder.setVisibility(visibility);
            return this;
        }

        public DailyData.Builder setCloudCover(double cloudCover) {
            hourlyBuilder.setCloudCover(cloudCover);
            return this;
        }

        public DailyData.Builder setPressure(double pressure) {
            hourlyBuilder.setPressure(pressure);
            return this;
        }

        public DailyData.Builder setOzone(double ozone) {
            hourlyBuilder.setOzone(ozone);
            return this;
        }
    }
}
