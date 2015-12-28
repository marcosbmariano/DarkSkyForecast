package com.mark.darkskyforecast.model;

/**
 * Created by mark on 12/21/15.
 *
 */
public class IconUtil {
    private static final String CLEAR_DAY = "clear-day";
    private static final String CLEAR_NIGHT = "clear-night";
    private static final String RAIN ="rain";
    private static final String SNOW = "snow";
    private static final String SLEET = "sleet";
    private static final String WIND = "wind";
    private static final String FOG = "fog";
    private static final String CLOUDY = "cloudy";
    private static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    private static final String PARTY_CLOUDY_NIGHT = "partly-cloudy-night";

    //the values to be returned must be an id to a drawable
    public static int getIcon(String icon){
        //the values to be returned are the ids of the images
        switch (icon){
            case CLEAR_DAY:
                return 0;
            case CLEAR_NIGHT:
                return 0;
            case RAIN:
                return 0;
            case SNOW:
                return 0;
            case SLEET:
                return 0;
            case WIND:
                return 0;
            case FOG:
                return 0;
            case CLOUDY:
                return 0;
            case PARTLY_CLOUDY_DAY:
                return 0;
            case PARTY_CLOUDY_NIGHT:
                return 0;
            default:
                return 0;

        }
    }

}
