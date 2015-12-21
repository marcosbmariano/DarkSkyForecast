package com.mark.darkskyforecast.network;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by mark on 12/21/15.
 */
public class VolleyHelper {
    private static final String APIKEY = "203bf0976335ed98863b556ed9f61f79";
    private static final String URL = "https://api.forecast.io/forecast/";   //APIKEY/LATITUDE,LONGITUDE

    //The second lets one query for a specific time, past or future (for many places, 60 years in the past to 10 years in the future):

   // https://api.forecast.io/forecast/APIKEY/LATITUDE,LONGITUDE,TIME


    public static JsonArrayRequest getGetArrayRequest(final Response.Listener<JSONArray> response,
                                                      final Response.ErrorListener errorListener,
                                                      final double latitude,
                                                      final double longitude) {

        Log.e("URL", "Tomar no cu");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getFormedURL(latitude, longitude),
                response, errorListener);
        Log.e("URL", request.getUrl());

        return request;
    }


    private static String getFormedURL(final double latitude, final double longitude ){
        return URL + APIKEY + "/" + latitude + "," + longitude;// + "," + (Calendar.getInstance().getTimeInMillis() /1000);
    }

    public static JsonObjectRequest getJsonRequest( Response.Listener<JSONObject> response,
                                                        Response.ErrorListener errorListener,final double latitude,
                                                         final double longitude){
        JsonObjectRequest result =
                new JsonObjectRequest(Request.Method.GET,getFormedURL(latitude, longitude),
                        response, errorListener);
        Log.e("URL", result.getUrl());
        return result;
    }
}
