package com.mark.darkskyforecast.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mark.darkskyforecast.fragments.MainForecastFrag;
import com.mark.darkskyforecast.fragments.NextDaysForecastFrag;


/**
 * Created by mark on 12/22/15.
 */
public class FragPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 2;
    private MainForecastFrag mMainFrag;
    private NextDaysForecastFrag mNextDaysFrag;


    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return getMainFrag();
        } else {
            return getNextDaysFrag();
        }
    }

    private MainForecastFrag getMainFrag(){
        if( mMainFrag == null){
            mMainFrag = new MainForecastFrag();
        }
        return mMainFrag;
    }

    private NextDaysForecastFrag getNextDaysFrag(){
        if( mNextDaysFrag == null){
            mNextDaysFrag = new NextDaysForecastFrag();
        }
        return  mNextDaysFrag;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
