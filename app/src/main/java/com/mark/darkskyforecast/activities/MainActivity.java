package com.mark.darkskyforecast.activities;




import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.activities.broadcast_receivers.AlarmReceiver;
import com.mark.darkskyforecast.adapters.FragPagerAdapter;
import com.mark.darkskyforecast.model.Forecast;


public class MainActivity extends AppCompatActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Toolbar mToolbar;
    private AlarmReceiver mAlarmReceiver;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPager();
        setupWidgets();
        //set initial alarm
        mAlarmReceiver = new AlarmReceiver();
        mAlarmReceiver.setAlarm(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Forecast.getInstance().updateForecast();
    }

    private void setupWidgets(){
        mToolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

    }

    private void setupViewPager(){
        mPager = (ViewPager)findViewById(R.id.vp_layout);
        mPagerAdapter = new FragPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if(mPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            mPager.setCurrentItem(mPager.getCurrentItem() -1);
        }
    }

}
