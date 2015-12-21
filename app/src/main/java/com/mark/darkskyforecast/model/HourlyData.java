package com.mark.darkskyforecast.model;

/**
 * Created by mark on 12/21/15.
 */
public class HourlyData {

    public HourlyData(){}

    private long time;
    private String summary;
    private int icon;
    private double precipIntensity;
    private double precipProbability;
    private double temperature;
    private double apparentTemperature;
    private double dewPoint;
    private double humidity;
    private double windSpeed;
    private double windBearing;
    private double visibility;
    private double cloudCover;
    private double pressure;
    private double ozone;


    public long getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public int getIcon() {
        return icon;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public double getPressure() {
        return pressure;
    }

    public double getOzone() {
        return ozone;
    }


    public static class Builder{
        private HourlyData result;

        public Builder(){
            result = new HourlyData();
        }

        public HourlyData build(){
            return result;
        }


        public HourlyData.Builder setTime(long time) {
            result.time = time;
            return this;
        }

        public HourlyData.Builder setSummary(String summary) {
            result.summary = summary;
            return this;
        }

        public HourlyData.Builder setIcon(int icon) {
            result.icon = icon;
            return this;
        }

        public HourlyData.Builder setPrecipIntensity(double precipIntensity) {
            result.precipIntensity = precipIntensity;
            return this;
        }

        public HourlyData.Builder setPrecipProbability(double precipProbability) {
            result.precipProbability = precipProbability;
            return this;
        }

        public HourlyData.Builder setTemperature(double temperature) {
            result.temperature = temperature;
            return this;
        }

        public HourlyData.Builder setApparentTemperature(double apparentTemperature) {
            result.apparentTemperature = apparentTemperature;
            return this;
        }

        public HourlyData.Builder setDewPoint(double dewPoint) {
            result.dewPoint = dewPoint;
            return this;
        }

        public HourlyData.Builder setHumidity(double humidity) {
            result.humidity = humidity;
            return this;
        }

        public HourlyData.Builder setWindSpeed(double windSpeed) {
            result.windSpeed = windSpeed;
            return this;
        }

        public HourlyData.Builder setWindBearing(double windBearing) {
            result.windBearing = windBearing;
            return this;
        }

        public HourlyData.Builder setVisibility(double visibility) {
            result.visibility = visibility;
            return this;
        }

        public HourlyData.Builder setCloudCover(double cloudCover) {
            result.cloudCover = cloudCover;
            return this;
        }

        public HourlyData.Builder setPressure(double pressure) {
            result.pressure = pressure;
            return this;
        }

        public HourlyData.Builder setOzone(double ozone) {
            result.ozone = ozone;
            return this;
        }

    }
}
