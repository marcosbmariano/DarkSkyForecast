package com.mark.darkskyforecast.activities;

import android.app.Dialog;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.helpers.DataHandler;
import com.mark.darkskyforecast.model.DailyData;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        DataHandler.ForecastObserver{
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataHandler.addObserver(this);
        //setupGoogleApiClient();
        setupWidgests();


        //new DailyData.Builder();



    }

    private void setupWidgests(){
        mTv = (TextView) findViewById(R.id.tvMain);
        mTv.setMovementMethod(new ScrollingMovementMethod());
    }


    private GoogleApiClient getGoogleApiClient(){
        if( mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApiIfAvailable(LocationServices.API)
                    .build();
        }
        return mGoogleApiClient;
    }

    private Location getLastLocation(){
         return LocationServices.FusedLocationApi.getLastLocation(getGoogleApiClient());
    }


    @Override
    protected void onStart() {
        getGoogleApiClient().connect();

        super.onStart();

    }


    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = getLastLocation();
        mTv.setText("Latitude " + mLastLocation.getLatitude() + " Longitude " + mLastLocation.getLongitude());
        if( mLastLocation != null){
            DataHandler.getForecasts(mLastLocation.getLatitude(),mLastLocation.getLongitude());

        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mTv.setText("Connection failed " + connectionResult.toString());
        //handle errors
        //SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED, SERVICE_DISABLED
       // GooglePlayServicesUtil.getErrorDialog( this, connectionResult.getErrorCode(), )
        //https://developers.google.com/android/reference/com/google/android/gms/common/GoogleApiAvailability#getErrorDialog(android.app.Activity, int, int)
    }


    @Override
    public void setForecastResult(String s) {
        mTv.setText(s);
    }
}
