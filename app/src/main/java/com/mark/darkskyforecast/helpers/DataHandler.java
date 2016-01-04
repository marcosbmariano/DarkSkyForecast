package com.mark.darkskyforecast.helpers;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mark.darkskyforecast.applications.MyApplication;

import com.mark.darkskyforecast.model.DailyData;
import com.mark.darkskyforecast.model.Forecast;
import com.mark.darkskyforecast.model.HourlyData;
import com.mark.darkskyforecast.model.ModelUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.Deque;
import java.util.List;


/**
 * Created by mark on 12/21/15.
 * this class handles the conversion of the JSON response to
 * a Forecast object based on DailyData and Hourly data object
 *
 */
public class DataHandler {
    private static final String DAILY_KEY = "daily";
    private static final String HOURLY_KEY = "hourly";
    private static final String DATA_KEY = "data";
    private static final String CURRENTLY_KEY = "currently";
    public static final String NEW_FORECAST_DOWNLOADED =
            "com.mark.darkskyforecast.NEW_FORECAST_DOWNLOADED";


    //this is a call back method used by Volley library to handle
    //a positive request
    public static Response.Listener<JSONObject> getJSONResponse(){

        return new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                if ( response != null){
                    Forecast.getInstance().setNextDaysForecast(setupData(response));
                    sendNewForecastBroadcast();
                }
            }
        };
    }

    private static void sendNewForecastBroadcast(){
        Intent intent = new Intent(NEW_FORECAST_DOWNLOADED);
        LocalBroadcastManager
                .getInstance(MyApplication.getAppContext()).sendBroadcast(intent);
    }

    //this method handles the JSONObject return by the call to the website
    public static List<DailyData> setupData(JSONObject response){
        List<DailyData> dailyData = new ArrayList<>();
        Deque<HourlyData> hourlyData;

        try {
            //get the current hourly data
            HourlyData now = getTodayDataFromJson(response);
            //get the next week data
            dailyData = getDailyData(response);
            //get all the hourly data for today
            hourlyData = getHourlyDataForToday(response);
            //set all the hourly data for today (today is index 0 )
            dailyData.get(0).setHourlyData(hourlyData);
            //set the currently hourly data for today as current
            dailyData.get(0).setCurrentHourlyData(now);

        } catch (JSONException e) {
            Log.e("DataHandler ERROR", e.toString());
        }
        return dailyData;
    }

    //get the "currentrly" hourly data
    private static HourlyData getTodayDataFromJson(JSONObject response) throws JSONException {
        JSONObject today = (JSONObject) response.get(CURRENTLY_KEY);
        return ModelUtil.getHourlyDataFromJson(today);
    }

    //get week data
    private static List<DailyData> getDailyData(JSONObject response) throws JSONException {
        List<DailyData> result = new ArrayList<>();
        JSONObject daily = (JSONObject) response.get(DAILY_KEY);
        JSONArray dailyArray = (JSONArray) daily.get(DATA_KEY);

        for (int i = 0; i < dailyArray.length(); i++) {
            result.add(ModelUtil.getDailyDataFromJson((dailyArray.getJSONObject(i))));
        }

        return result;
    }
    //get only the hourly data for today
    private static Deque<HourlyData> getHourlyDataForToday(JSONObject response) throws JSONException {
        Deque<HourlyData> result = new ArrayDeque<>();
        JSONObject hourly = (JSONObject) response.get(HOURLY_KEY);
        JSONArray hourlyArray = (JSONArray) hourly.get(DATA_KEY);
        boolean isHourlyDataFromToday;
        int index = 0;

        if (hourlyArray.length() > 0) {
            isHourlyDataFromToday = DateHelper.isToday(hourlyArray.getJSONObject(index).getLong("time"));

            while (isHourlyDataFromToday) {
                result.push(ModelUtil.getHourlyDataFromJson(hourlyArray.getJSONObject(index++)));
                isHourlyDataFromToday = DateHelper.isToday(hourlyArray.getJSONObject(index).getLong("time"));
            }
        }

        return result;
    }

    //this method handles the error if the call to Volley fails
    public static Response.ErrorListener getErrorResponse(){ //TODO must me improved
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.e("ERROR", " "+ error.toString());
            }
        };
    }


}
