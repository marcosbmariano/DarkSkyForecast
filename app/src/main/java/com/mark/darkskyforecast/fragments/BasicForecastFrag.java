package com.mark.darkskyforecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.darkskyforecast.R;

/**
 * Created by mark on 12/22/15.
 */
public class BasicForecastFrag extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.basic_forecast_layout, container, false);
        return rootView;
    }
}
