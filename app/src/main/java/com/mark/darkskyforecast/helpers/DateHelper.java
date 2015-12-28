package com.mark.darkskyforecast.helpers;

import java.util.Calendar;

/**
 * Created by mark on 12/25/15.
 */
public class DateHelper {

    public static boolean isToday(long timeStamp){
        return isSameDay(Calendar.getInstance(), timeStampToCalendar(timeStamp));
    }

    public static boolean isHourCurrent(long timestamp){
        if ( isToday(timestamp)){
            return isSameHour(Calendar.getInstance(), timeStampToCalendar(timestamp));
        }
        return false;
    }

    private static boolean isSameHour(Calendar reference, Calendar comparedTime){
        return (reference.get(Calendar.HOUR_OF_DAY) ==
                comparedTime.get(Calendar.HOUR_OF_DAY));
    }

    private static boolean isSameDay(Calendar referenceTimeStamp, Calendar comparedTimeStamp){
        return ( referenceTimeStamp.get(Calendar.DAY_OF_YEAR) ==
                comparedTimeStamp.get(Calendar.DAY_OF_YEAR));
    }

    public static Calendar timeStampToCalendar(long timeStamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000);
        return calendar;
    }


}
