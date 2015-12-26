package com.mark.darkskyforecast.model;

import android.test.AndroidTestCase;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by mark on 12/21/15.
 */
public class ModelUtilsTest extends AndroidTestCase{
    private JSONObject forecast = JsonObjectGenForTest.getForecastJsonObject();


    @Override
    protected void setUp() throws Exception {



        Iterator<String> keys = forecast.keys();
        DailyData dailyData = null;


        //JSONObject currently = (JSONObject)forecast.get("currently");
        JSONObject daily = (JSONObject)forecast.get("daily");
        JSONObject hourly = (JSONObject)forecast.get("hourly");

        JSONArray dailyArray = (JSONArray)daily.get("data");
        JSONArray hourlyArray = (JSONArray)hourly.get("data");

        for( int i = 0; i < dailyArray.length(); i++){
            displayData(ModelUtil.getDailyDataFromJson((dailyArray.getJSONObject(i))));

        }

        for( int i = 0; i < hourlyArray.length(); i++ ){
            displayData(ModelUtil.getHourlyDataFromJson(hourlyArray.getJSONObject(i)));
        }

    }

    private void displayData(DailyData dailyData){
        Log.e("test","Time " + dailyData.getTime());
        Log.e("test","Summary " + dailyData.getSummary());
        Log.e("test","Icon  " + dailyData.getIcon());
        Log.e("test","SunsetTime  " + dailyData.getSunsetTime());
        Log.e("test","Precip Intensity " + dailyData.getPrecipIntensity());
        Log.e("test","PrecipProbability " + dailyData.getPrecipProbability());
        Log.e("test","Temperature " + dailyData.getTemperature());
        Log.e("test","ApparentTemperature " + dailyData.getApparentTemperature());
        Log.e("test","DewPoint " + dailyData.getDewPoint());
        Log.e("test", "Humidity " +dailyData.getHumidity());
        Log.e("test", "tWindSpeed " +dailyData.getWindSpeed());
        Log.e("test", "WindBearing " +dailyData.getWindBearing());
        Log.e("test", "Visibility " +dailyData.getVisibility());
        Log.e("test", "CloudCover " +dailyData.getCloudCover());
        Log.e("test", "Pressure " +dailyData.getPressure());
        Log.e("test", "Ozone " +dailyData.getOzone());
        Log.e("test", "SunriseTime " +dailyData.getSunriseTime());
        Log.e("test", "MoonPhase " +dailyData.getMoonPhase());
        Log.e("test", "PrecipIntensityMax " +dailyData.getPrecipIntensityMax());
        Log.e("test", "PrecipIntensityMaxTime " +dailyData.getPrecipIntensityMaxTime());
        Log.e("test", "PrecipType " +dailyData.getPrecipType());
        Log.e("test", "TemperatureMin " +dailyData.getTemperatureMin());
        Log.e("test", "TemperatureMinTime " +dailyData.getTemperatureMinTime());
        Log.e("test", "TemperatureMax " +dailyData.getTemperatureMax());
        Log.e("test", "TemperatureMaxTime " +dailyData.getTemperatureMaxTime());
        Log.e("test", "ApparentTemperatureMin " +dailyData.getApparentTemperatureMin());
        Log.e("test", "ApparentTemperatureMinTime " +dailyData.getApparentTemperatureMinTime());
        Log.e("test", "ApparentTemperatureMax " +dailyData.getApparentTemperatureMax());
        Log.e("test", "ApparentTemperatureMaxTime " +dailyData.getApparentTemperatureMaxTime());
        Log.e("Another day", "------------------------------------------");
    }

    private void displayData(HourlyData hourlyData){

        Log.e("test","Time " + hourlyData.getTime());
        Log.e("test","Summary " + hourlyData.getSummary());
        Log.e("test","Icon  " + hourlyData.getIcon());
        Log.e("test","Precip Intensity " + hourlyData.getPrecipIntensity());
        Log.e("test","PrecipProbability " + hourlyData.getPrecipProbability());
        Log.e("test","Temperature " + hourlyData.getTemperature());
        Log.e("test","ApparentTemperature " + hourlyData.getApparentTemperature());
        Log.e("test","DewPoint " + hourlyData.getDewPoint());
        Log.e("test", "Humidity " +hourlyData.getHumidity());
        Log.e("test", "WindSpeed " +hourlyData.getWindSpeed());
        Log.e("test", "WindBearing " +hourlyData.getWindBearing());
        Log.e("test", "Visibility " +hourlyData.getVisibility());
        Log.e("test", "CloudCover " +hourlyData.getCloudCover());
        Log.e("test", "Pressure " +hourlyData.getPressure());
        Log.e("test", "Ozone " +hourlyData.getOzone());
        Log.e("Another hour", "------------------------------------------");

    }


}
