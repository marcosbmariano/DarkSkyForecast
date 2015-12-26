package com.mark.darkskyforecast.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mark on 12/21/15.
 */
public class JsonObjectGenForTest {


    public static JSONObject getForecastJsonObject(){
        JSONObject forecastObject = new JSONObject();

        try {

            forecastObject.put("latitude",51.503407 );
            forecastObject.put("longitude", -0.127592);
            forecastObject.put("timezone", "Europe/London");
            forecastObject.put("offset", 0);
            forecastObject.put("currently", getCurrentlyJsonObject());
            forecastObject.put("hourly", getHourlyJsonObject());
            forecastObject.put("daily", getDailyJsonObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return forecastObject;
    }

    private static JSONObject getCurrentlyJsonObject(){
        JSONObject currently = new JSONObject();
        try {
            currently.put("time",1450310993);
            currently.put("summary","Mostly Cloudy");
            currently.put("icon","partly-cloudy-night");
            currently.put("nearestStormDistance",216);
            currently.put("nearestStormBearing",218);
            currently.put("precipIntensity",0);
            currently.put("precipProbability",0);
            currently.put("temperature",56.73);
            currently.put("apparentTemperature",56.73);
            currently.put("dewPoint",52.56);
            currently.put("humidity",0.86);
            currently.put("windSpeed",11.15);
            currently.put("windBearing",220);
            currently.put("visibility",9.83);
            currently.put("cloudCover",0.71);
            currently.put("pressure",1019.45);
            currently.put("ozone",267.87);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currently;
    }


    private static JSONObject getHourlyJsonObject(){
        JSONObject hourly = new JSONObject();
        try {
            hourly.put("summary","Light rain tomorrow evening.");
            hourly.put("icon","rain");
            hourly.put("data", getHourlyJsonDataArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hourly;
    }

    private static JSONArray getHourlyJsonDataArray() {
        JSONArray result = new JSONArray();
        JSONObject arrayItem;

        for (int i = 0; i < 12; i++) {
            arrayItem = new JSONObject();

            try {
                arrayItem.put("time", 1450357200);
                arrayItem.put("summary", "Mostly Cloudy");
                arrayItem.put("icon", "partly-cloudy-day");
                arrayItem.put("precipIntensity", 0);
                arrayItem.put("precipProbability",0);
                arrayItem.put("temperature", 59.88);
                arrayItem.put("apparentTemperature", 59.88);
                arrayItem.put("dewPoint", 48.55);
                arrayItem.put("humidity", 0.66);
                arrayItem.put("windSpeed", 14.63);
                arrayItem.put("windBearing", 202);
                arrayItem.put("visibility", 10);
                arrayItem.put("cloudCover", 0.71);
                arrayItem.put("pressure", 1014.54);
                arrayItem.put("ozone", 284.39);

                result.put(arrayItem);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    private static JSONObject getDailyJsonObject(){
        JSONObject result = new JSONObject();

        try {
            result.put("icon","rain");
            result.put("summary","Light rain today through Wednesday," +
                    " with temperatures peaking at 62°F on Saturday.");
            result.put("data", getDailyDataArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static JSONArray getDailyDataArray(){
        JSONArray result = new JSONArray();
        JSONObject arrayItem;

        for (int i = 0; i < 12; i++) {

            try {
                arrayItem = new JSONObject();
                arrayItem.put("time",1450310400);
                arrayItem.put("summary","Light rain in the evening.");
                arrayItem.put("icon","rain");
                arrayItem.put("sunriseTime",1450339341);
                arrayItem.put("sunsetTime",1450367592);
                arrayItem.put("moonPhase",0.21);
                arrayItem.put("precipIntensity",0.0022);
                arrayItem.put("precipIntensityMax",0.0111);
                arrayItem.put("precipIntensityMaxTime",1450375200);
                arrayItem.put("precipProbability",0.5);
                arrayItem.put("precipType","rain");
                arrayItem.put("temperatureMin",53.98);
                arrayItem.put("temperatureMinTime",1450393200);
                arrayItem.put("temperatureMax",59.88);
                arrayItem.put("temperatureMaxTime",1450357200);
                arrayItem.put("apparentTemperatureMin",53.98);
                arrayItem.put("apparentTemperatureMinTime",1450393200);
                arrayItem.put("apparentTemperatureMax",59.88);
                arrayItem.put("apparentTemperatureMaxTime",1450357200);
                arrayItem.put("dewPoint",49.09);
                arrayItem.put("humidity",0.77);
                arrayItem.put("windSpeed",11.77);
                arrayItem.put("windBearing",209);
                arrayItem.put("visibility",10);
                arrayItem.put("cloudCover",0.69);
                arrayItem.put("pressure",1015.85);
                arrayItem.put("ozone",280.19);
                result.put(arrayItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
