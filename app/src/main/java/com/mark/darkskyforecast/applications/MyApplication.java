package com.mark.darkskyforecast.applications;

import android.app.Application;
import android.content.Context;

/**
 * Created by mark on 12/16/15.
 */
public class MyApplication extends Application {
    private static Context mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
}
