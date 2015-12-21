package com.mark.darkskyforecast.network;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mark.darkskyforecast.applications.MyApplication;

/**
 * Created by mark on 12/16/15.
 */
public class VolleySingleton {
    private static RequestQueue mRequestQueue;

    private VolleySingleton(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleySingleton getInstance(){

        return Holder.mInstance;
    }

    public  RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    private static class Holder{
        private final static VolleySingleton mInstance =
                new VolleySingleton(MyApplication.getAppContext());
    }
}
