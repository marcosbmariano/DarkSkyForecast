package com.mark.darkskyforecast.helpers;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mark.darkskyforecast.network.VolleySingleton;
import com.mark.darkskyforecast.network.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Created by mark on 12/21/15.
 */
public class DataHandler {
    //latitude
    //longitude
    //timezone
    //offset  //
    private static ForecastObserver mObserver;


    public static void addObserver(ForecastObserver observer){
        mObserver = observer;
    }



    public static void getForecasts(final double latitude,
                                    final double longitude){

        Log.e("requenstin" , "Rquesting motherfucker");
        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();

        queue.add(VolleyHelper.getJsonRequest(getJsonArrayResponse(),
                getErrorResponse(), latitude, longitude));

    }



    private static Response.Listener<JSONObject> getJsonArrayResponse(){
        return new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                StringBuilder bd = new StringBuilder();
                if ( response != null){
                    Log.e("jsjsjsjs", response.toString());



                        try {
                            Iterator<String> iterator = response.keys();

                            while (iterator.hasNext()){
                                String key = iterator.next();

                                Object obj = response.get(key);
                                if ( obj instanceof JSONObject){
                                    bd.append("Json key " + key +  "\n");
                                    Iterator<String> internalKey = ((JSONObject) obj).keys();

                                    while(internalKey.hasNext()){
                                        bd.append("Internal key " + internalKey.next() + "\n");
                                    }
                                }else{
                                    bd.append("Key " + key + " " + response.get(key) + "\n");
                                }


                            }



                            //Log.d("jsjsjsjs", response.getString("summary"));
                            //bd.append(response.getString("summary"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                }

              mObserver.setForecastResult(bd.toString());

            }
        };
    }


    private static Response.ErrorListener getErrorResponse(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mObserver.setForecastResult("ERRRRRO" + error.toString());
            }
        };
    }

    public interface ForecastObserver{
        void setForecastResult(String s);
    }




}
