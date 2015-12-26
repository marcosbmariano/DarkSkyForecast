package com.mark.darkskyforecast.helpers;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mark.darkskyforecast.model.DailyData;
import com.mark.darkskyforecast.model.Forecast;
import com.mark.darkskyforecast.model.HourlyData;
import com.mark.darkskyforecast.model.ModelUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by mark on 12/21/15.
 *
 *
 *
 */
public class DataHandler {
    private static final String DAILY_KEY = "daily";
    private static final String HOURLY_KEY = "hourly";
    private static final String DATA_KEY = "data";

//    public static void getForecasts(final double latitude,
//                                    final double longitude){
//
//        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
//        queue.add(VolleyHelper.getJsonRequest(getJSONResponse(),
//                getErrorResponse(), latitude, longitude));
//    }

    public static Response.Listener<JSONObject> getJSONResponse(){

        return new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                if ( response != null){
                    Log.d("OnResponse()", response.toString());

                    Forecast.getInstance().setNextDaysForecast(getDailyData(response));
                }
            }
        };
    }

    public static List<DailyData> getDailyData(JSONObject response){ //TODO must be improved
        List<DailyData> dailyData = new ArrayList<>();
        List<HourlyData> hourlyData = new ArrayList<>();

        try {
            Log.e("Is today ", " " + isToday(((JSONObject) response.get("currently")).getLong("time")));
            //make this as today

            JSONObject daily = (JSONObject) response.get(DAILY_KEY);
            JSONArray dailyArray = (JSONArray)daily.get(DATA_KEY);
            dailyData = getDailyData(dailyArray);

            JSONObject hourly = (JSONObject)response.get(HOURLY_KEY);
            JSONArray hourlyArray = (JSONArray)hourly.get(DATA_KEY);
            hourlyData = getHourlyDataForToday(hourlyArray);

            dailyData.get(0).setHourlyData(hourlyData);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dailyData;
    }


    private static List<DailyData> getDailyData( JSONArray dailyArray){
        List<DailyData> result = new ArrayList<>();
        for( int i = 0; i < dailyArray.length(); i++){
            try {
                result.add(ModelUtil.getDailyDataFromJson((dailyArray.getJSONObject(i))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//Log.e("Is today ", " " + isToday(dailyArray.getJSONObject(i).getLong("time")));


    private static List<HourlyData> getHourlyDataForToday( JSONArray hourlyData ){
        List<HourlyData> result = new ArrayList<>();
        boolean isHourlyDataFromToday = false;
        int index = 0;
        if ( hourlyData.length() > 0){
            try {
                isHourlyDataFromToday = isToday(hourlyData.getJSONObject(index).getLong("time"));

                while (isHourlyDataFromToday){
                    result.add(ModelUtil.getHourlyDataFromJson(hourlyData.getJSONObject(index++)));
                    Log.e("Is hourly today ", " " + isToday(hourlyData.getJSONObject(index).getLong("time")));
                    isHourlyDataFromToday = isToday(hourlyData.getJSONObject(index).getLong("time"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    //Check if the calendars belong to the same day
    private static boolean isToday(long timeStamp){
        return isSameDay(Calendar.getInstance(), timeStampToDate(timeStamp));
    }

    private static boolean isSameDay(Calendar referenceTimeStamp, Calendar comparedTimeStamp){
        return ( referenceTimeStamp.get(Calendar.DAY_OF_YEAR) ==
                comparedTimeStamp.get(Calendar.DAY_OF_YEAR));
    }

    private static Calendar timeStampToDate(long timeStamp){ //TODO move to a date utils class
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000);
        return calendar;
    }

    public static Response.ErrorListener getErrorResponse(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.e("ERRRRRO", " "+ error.toString());
            }
        };
    }


}
