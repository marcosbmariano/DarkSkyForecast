package com.mark.darkskyforecast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.darkskyforecast.R;
import com.mark.darkskyforecast.model.DailyData;
import com.mark.darkskyforecast.model.Forecast;

import java.util.List;

/**
 * Created by mark on 12/22/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HV> implements Forecast.ForecastObserver{
    private static LayoutInflater mInflater;
    private List<DailyData> mNextDays;


    public RecyclerAdapter(Context context){
        Forecast.addObserver(this);
        mInflater = LayoutInflater.from(context);
        mNextDays = Forecast.getInstance().getNextDaysForecast();
    }


    @Override
    public HV onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.basic_forecast_layout, parent, false);
        return new HV(view);
    }

    @Override
    public void onBindViewHolder(HV holder, int position) {
        DailyData currentDay = mNextDays.get(position);
        holder.setSummary("Summary " + currentDay.getSummary());
        holder.setTemp("Max Temp: " + currentDay.getTemperatureMax());
        holder.setMinMaxTemp("Min Temp: "+ currentDay.getTemperatureMin() );
        holder.setDate(currentDay.getStringDate());
    }

    @Override
    public int getItemCount() {
        return mNextDays.size();
    }

    @Override
    public void setForecastResult() {
        mNextDays = Forecast.getInstance().getNextDaysForecast();
        notifyDataSetChanged();
    }

    class HV extends RecyclerView.ViewHolder{
        private TextView mTemp;
        private TextView mSummary;
        private TextView mMinMaxTemp;
        private TextView mDate;



        public HV(View itemView) {
            super(itemView);
            setupViews(itemView);
        }

        private void setupViews(View v){
            mTemp = (TextView)v.findViewById(R.id.tvTemp);
            mSummary = (TextView)v.findViewById(R.id.tvSummary);
            mMinMaxTemp = (TextView)v.findViewById(R.id.tvMinMax);
            mDate = (TextView)v.findViewById(R.id.tvDate);
        }
        private void setDate(String date){
            mDate.setText(date);
        }

        private void setTemp(String temp){
            mTemp.setText(temp);
        }

        private void setSummary(String s){
            mSummary.setText(s);
        }

        private void setMinMaxTemp(String s){
            mMinMaxTemp.setText(s);
        }

    }
}
