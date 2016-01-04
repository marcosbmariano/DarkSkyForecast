package com.mark.darkskyforecast.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.adapters.RecyclerAdapter;
import com.mark.darkskyforecast.applications.MyApplication;
import com.mark.darkskyforecast.helpers.DataHandler;

/**
 * Created by mark on 12/22/15.
 */
public class NextDaysForecastFrag extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private MyReceiver mReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupReceiver();
    }

    private void setupReceiver(){
        mReceiver = new MyReceiver();
        LocalBroadcastManager.getInstance(MyApplication.getAppContext())
                .registerReceiver(mReceiver, new IntentFilter(DataHandler.NEW_FORECAST_DOWNLOADED));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup)inflater.inflate(R.layout.next_days_frag_layout,container, false);

        setupRecyclerView(rootView);
        return rootView;
    }

    private void setupRecyclerView(ViewGroup rootView){
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroy() {
        if( null != mReceiver){
            LocalBroadcastManager.getInstance(getContext())
                    .unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        super.onDestroy();
    }

    private class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if( intent.getAction().equals(DataHandler.NEW_FORECAST_DOWNLOADED)){
                mAdapter.updateData();
                Log.d("NexDaysForecast", "OnReceive");
            }
        }
    }

}
