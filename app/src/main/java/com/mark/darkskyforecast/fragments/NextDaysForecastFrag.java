package com.mark.darkskyforecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.adapters.RecyclerAdapter;
import com.mark.darkskyforecast.model.Forecast;

/**
 * Created by mark on 12/22/15.
 */
public class NextDaysForecastFrag extends Fragment implements Forecast.ForecastObserver{
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startObservingToForecast();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.next_days_frag_layout,container, false);
        setupRecyclerView(rootView);

        return rootView;
    }

    private void setupRecyclerView(ViewGroup rootView){
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void startObservingToForecast(){
        Forecast.addObserver(this);
    }

    public void stopObservingForecast(){
        Forecast.removeObserver();
    }



    @Override
    public void setForecastResult() {

    }
}
