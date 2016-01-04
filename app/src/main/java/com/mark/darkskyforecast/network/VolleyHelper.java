package com.mark.darkskyforecast.network;


import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;



/**
 * Created by mark on 12/21/15.
 * This class handles all the volley http calls
 */
public class VolleyHelper {
    private static final String APIKEY = "203bf0976335ed98863b556ed9f61f79";
    private static final String URL = "https://api.forecast.io/forecast/";

   // https://api.forecast.io/forecast/APIKEY/LATITUDE,LONGITUDE,TIME


    private static String getFormedURL(final double latitude, final double longitude ){
        return URL + APIKEY + "/" + latitude + "," + longitude;
    }

    public static JsonObjectRequest getJsonRequest( Response.Listener<JSONObject> response,
                                                        Response.ErrorListener errorListener,final double latitude,
                                                         final double longitude){
        JsonObjectRequest result =
                new JsonObjectRequest(Request.Method.GET,getFormedURL(latitude, longitude),
                        response, errorListener);
        Log.d("URL", result.getUrl());
        return result;
    }
}
