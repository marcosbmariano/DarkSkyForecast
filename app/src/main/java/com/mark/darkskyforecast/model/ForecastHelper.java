package com.mark.darkskyforecast.model;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.mark.darkskyforecast.applications.MyApplication;
import com.mark.darkskyforecast.helpers.DataHandler;
import com.mark.darkskyforecast.network.VolleyHelper;
import com.mark.darkskyforecast.network.VolleySingleton;

/**
 * Created by mark on 12/23/15.
 * this class is responsible to make the get call to the API
 *
 */
public class ForecastHelper implements  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static GoogleApiClient mGoogleApiClient;

    public static ForecastHelper getInstance(){
        return Holder.mInstance;
    }

    private ForecastHelper(){
        mGoogleApiClient = getNewGoogleApiClient();
    }

    private static class Holder{
        private static final ForecastHelper mInstance = new ForecastHelper();
    }

    public void getForecasts(){
        mGoogleApiClient.connect();
    }

    private GoogleApiClient getNewGoogleApiClient(){
        return new GoogleApiClient.Builder(MyApplication.getAppContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApiIfAvailable(LocationServices.API)
                .build();
    }

    private Location getLastLocation(){
       return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location lastLocation = getLastLocation();
        getForecasts(lastLocation.getLatitude(), lastLocation.getLongitude());
        mGoogleApiClient.disconnect();
    }
    //TODO prevent multiple calls
    private static void getForecasts(final double latitude,
                                     final double longitude){
        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        queue.add(VolleyHelper.getJsonRequest(DataHandler.getJSONResponse(),
                DataHandler.getErrorResponse(), latitude, longitude));

    }

    @Override
    public void onConnectionSuspended(int i) {
        //TODO fix this
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("NEtwork Service", "Connection Failed");
    }
}
