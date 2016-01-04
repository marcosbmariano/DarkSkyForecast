package com.mark.darkskyforecast.broadcast_receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.mark.darkskyforecast.services.SchedulingService;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by mark on 1/4/16.
 * When the alarm fires, this WakefulBroadcastReceiver receives the broadcast Intent
 * and then starts the IntentService SchedulingService to do some work.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingAlarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        startWakefulService(context, new Intent(context, SchedulingService.class));
    }

    public void setAlarm(Context context){
        mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        mPendingAlarmIntent = PendingIntent.getBroadcast(context,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //set the alarm's trigger to ~1:30 a.m
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //random minuts to avoid all apps calling the server at same time
        Random random = new Random();
        calendar.set(Calendar.MINUTE, (10 + random.nextInt(20)));

        //set the alarm to fire at approximately 0:20, according to the device's clock
        //and to repeat once a day
        mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendingAlarmIntent);

        setupBootReceiver(context);
    }
    // Enable BootReceiver to automatically restart the alarm when the
    // device is rebooted.
    private void setupBootReceiver(Context context){
        ComponentName bootReceiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(bootReceiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }

    /**
     * Cancels the alarm.
     * @param context
     */
    public void cancelAlarm(Context context) {
        // If the alarm has been set, cancel it.
        if (mAlarmManager!= null && null != mPendingAlarmIntent) {
            mAlarmManager.cancel(mPendingAlarmIntent);
        }

        // Disable BootReceiver so that it doesn't automatically restart the
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
