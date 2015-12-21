package com.mark.darkskyforecast.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by mark on 12/21/15.
 */
public class ModelUtil {

    private static final String TIME = "time";
    private static final String SUMMARY = "summary";
    private static final String ICON = "icon";
    private static final String NEAR_STORM = "nearestStormDistance";
    private static final String NEAR_STORM_BEARING = "nearestStormBearing";
    private static final String PRECIP_INTENSITY = "precipIntensity";
    private static final String PRECIP_PROBABLILITY = "precipProbability";
    private static final String TEMP = "temperature";
    private static final String APP_TEMP = "apparentTemperature";
    private static final String DEW_POINT = "dewPoint";
    private static final String HUMIDITY = "humidity";
    private static final String WIND_SPEED = "windSpeed";
    private static final String WIND_BEARING = "windBearing";
    private static final String VISIBILITY = "visibility";
    private static final String CLOUD_COVER = "cloudCover";
    private static final String PRESSURE = "pressure";
    private static final String OZONE = "ozone";
    private static final String SUNRIZE_TIME = "sunriseTime";
    private static final String SUNSET_TIME =  "sunsetTime";
    private static final String MOON_PHASE = "moonPhase";
    private static final String PRECIP_INTENSITY_MAX = "precipIntensityMax";
    private static final String PRECIP_ITENSITY_MAX_TIME = "precipIntensityMaxTime";
    private static final String PRECIP_TYPE = "precipType";
    private static final String TEMP_MIN = "temperatureMin";
    private static final String TEMP_MIN_TIME = "temperatureMinTime";
    private static final String TEMP_MAX = "temperatureMax";
    private static final String TEMP_MAX_TIME = "temperatureMaxTime";
    private static final String APP_TEMP_MIN =  "apparentTemperatureMin";
    private static final String APP_TEMP_MIN_TIME = "apparentTemperatureMinTime";
    private static final String APP_TEMP_MAX = "apparentTemperatureMax";
    private static final String APP_TEMP_MAX_TIME ="apparentTemperatureMaxTime";

    public static DailyData getDailyDataFromJson(JSONObject obj) {
        DailyData.Builder builder = new DailyData.Builder();
        Iterator<String> keys = obj.keys();

        String key;
        while (keys.hasNext()) {
            key = keys.next();

            try {
                switch (key) {
                    case APP_TEMP_MAX_TIME:
                        builder.setApparentTemperatureMaxTime(obj.getLong(key));
                        break;
                    case APP_TEMP_MAX:
                        builder.setApparentTemperatureMax(obj.getDouble(key));
                        break;
                    case APP_TEMP_MIN_TIME:
                        builder.setApparentTemperatureMinTime(obj.getLong(key));
                        break;
                    case APP_TEMP_MIN:
                        builder.setApparentTemperatureMin(obj.getDouble(key));
                        break;
                    case TEMP_MAX_TIME:
                        builder.setTemperatureMaxTime(obj.getLong(key));
                        break;
                    case TEMP_MAX:
                        builder.setTemperatureMax(obj.getDouble(key));
                        break;
                    case TEMP_MIN_TIME:
                        builder.setTemperatureMinTime(obj.getLong(key));
                        break;
                    case TEMP_MIN:
                        builder.setTemperatureMin(obj.getDouble(key));
                        break;
                    case PRECIP_TYPE:
                        builder.setPrecipType(obj.getDouble(key));
                        break;
                    case PRECIP_ITENSITY_MAX_TIME:
                        builder.setPrecipIntensityMaxTime(obj.getLong(key));
                        break;
                    case PRECIP_INTENSITY_MAX:
                        builder.setPrecipIntensityMax(obj.getDouble(key));
                        break;
                    case MOON_PHASE:
                        builder.setMoonPhase(obj.getDouble(key));
                        break;
                    case SUNRIZE_TIME:
                        builder.setSunriseTime(obj.getLong(key));
                        break;
                    case SUNSET_TIME:
                        builder.setSunsetTime(obj.getLong(key));
                        break;
                    case TIME:
                        builder.setTime(obj.getLong(key));
                        break;
                    case SUMMARY:
                        builder.setSummary(obj.getString(key));
                        break;
                    case ICON:
                        builder.setIcon(IconUtil.getIcon(obj.getString(key)));
                        break;
                    case NEAR_STORM:
                        //TODO fix this;
                        break;
                    case NEAR_STORM_BEARING:
                        //TODO fix this
                        break;
                    case PRECIP_INTENSITY:
                        builder.setPrecipIntensity(obj.getDouble(key));
                        break;
                    case PRECIP_PROBABLILITY:
                        builder.setPrecipProbability(obj.getDouble(key));
                        break;
                    case DEW_POINT:
                        builder.setDewPoint(obj.getDouble(key));
                        break;
                    case HUMIDITY:
                        builder.setHumidity(obj.getDouble(key));
                        break;
                    case WIND_SPEED:
                        builder.setWindSpeed(obj.getDouble(key));
                        break;
                    case WIND_BEARING:
                        builder.setWindBearing(obj.getDouble(key));
                        break;
                    case VISIBILITY:
                        builder.setVisibility(obj.getDouble(key));
                        break;
                    case CLOUD_COVER:
                        builder.setCloudCover(obj.getDouble(key));
                        break;
                    case PRESSURE:
                        builder.setPressure(obj.getDouble(key));
                        break;
                    case OZONE:
                        builder.setOzone(obj.getDouble(key));
                        break;
                    default:
                        //do nothing

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return builder.build();
    }


    public static HourlyData getHourlyDataFromJson(JSONObject obj){
        HourlyData.Builder builder = new HourlyData.Builder();

        Iterator<String> iterator = obj.keys();

        String key;
        while(iterator.hasNext()){
            key = iterator.next();

            try {
                switch (key) {
                    case TIME:
                        builder.setTime(obj.getLong(key));
                        break;
                    case SUMMARY:
                        builder.setSummary(obj.getString(key));
                        break;
                    case ICON:
                        builder.setIcon(IconUtil.getIcon(obj.getString(key)));
                        break;
                    case PRECIP_INTENSITY:
                        builder.setPrecipIntensity(obj.getDouble(key));
                        break;
                    case PRECIP_PROBABLILITY:
                        builder.setPrecipProbability(obj.getDouble(key));
                        break;
                    case TEMP:
                        builder.setTemperature(obj.getDouble(key));
                        break;
                    case APP_TEMP:
                        builder.setApparentTemperature(obj.getDouble(key));
                        break;
                    case DEW_POINT:
                        builder.setDewPoint(obj.getDouble(key));
                        break;
                    case HUMIDITY:
                        builder.setHumidity(obj.getDouble(key));
                        break;
                    case WIND_SPEED:
                        builder.setWindSpeed(obj.getDouble(key));
                        break;
                    case WIND_BEARING:
                        builder.setWindBearing(obj.getDouble(key));
                        break;
                    case VISIBILITY:
                        builder.setVisibility(obj.getDouble(key));
                        break;
                    case CLOUD_COVER:
                        builder.setCloudCover(obj.getDouble(key));
                        break;
                    case PRESSURE:
                        builder.setPressure(obj.getDouble(key));
                        break;
                    case OZONE:
                        builder.setOzone(obj.getDouble(key));
                        break;
                    default:
                        //do nothing

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return builder.build();
    }


}
