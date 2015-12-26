package com.mark.darkskyforecast.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mark on 12/21/15.
 */
public class DailyData {

    private final List<HourlyData> mHourlyData;
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
        mHourlyData = new ArrayList<>();
    }

    public void setHourlyData(List<HourlyData> hourlyData){
        HourlyData data = hourlyData.remove(0);
        setCurrentHourlyData(data);
        mHourlyData.addAll(hourlyData);
    }

    private void setCurrentHourlyData(HourlyData data){
        currentHourlyData = data;
    }

    public List<HourlyData> getHourlyData() {
        return mHourlyData;
    } //TODO fix this

    public HourlyData getCurrentHourlyData() {
        return currentHourlyData;
    }


    public long getLastHourlyDataTime(){
        long result = 0;
        if( getHourlyData() != null && getHourlyData().size() > 0){
            int lastDataIndex = getHourlyData().size() -1;
            result = getHourlyData().get(lastDataIndex).getTime();
        }
        return result;
    }
    public String getStringDate(){
        return new SimpleDateFormat("EE MMM d").format(getDate());
    }

    private Date getDate(){  //TODO move to a date utils class
        if ( getTime() == 0 ){
            return new Date();
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(getTime() * 1000);
            return calendar.getTime();
        }
    }

    public long getTime() {
        return getCurrentHourlyData().getTime();
    }

    public String getSummary() {
        return getCurrentHourlyData().getSummary();
    }

    public int getIcon() {
        return getCurrentHourlyData().getIcon();
    }

    public double getPrecipIntensity() {
        return getCurrentHourlyData().getPrecipIntensity();
    }

    public double getPrecipProbability() {
        return getCurrentHourlyData().getPrecipProbability();
    }

    public double getTemperature() {
        return getCurrentHourlyData().getTemperature();
    }

    public double getApparentTemperature() {
        return getCurrentHourlyData().getApparentTemperature();
    }

    public double getDewPoint() {
        return getCurrentHourlyData().getDewPoint();
    }

    public double getHumidity() {
        return getCurrentHourlyData().getHumidity();
    }

    public double getWindSpeed() {
        return getCurrentHourlyData().getWindSpeed();
    }

    public double getWindBearing() {
        return getCurrentHourlyData().getWindBearing();
    }

    public double getVisibility() {
        return getCurrentHourlyData().getVisibility();
    }

    public double getCloudCover() {
        return getCurrentHourlyData().getCloudCover();
    }

    public double getPressure() {
        return getCurrentHourlyData().getPressure();
    }

    public double getOzone() {
        return getCurrentHourlyData().getOzone();
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
