package com.example.garageapp;

import java.util.Calendar;
import java.util.Locale;

public final class Utility {
    private static final int SECONDS = 60;
    private static final int MINUTES = 60;
    private static final int HOURS = 24;
    private static final int MILLISECONDS = 1000;


    public static String parseTime(int time) {
        int seconds = (time /= MILLISECONDS) % SECONDS;
        int minutes = (time /= SECONDS) % MINUTES;
        int hours = (time /= MINUTES)  % HOURS;
        int days = time / HOURS;
        return String.format(Locale.ENGLISH, "Days: %d \t Time: %02d:%02d:%02d", days, hours, minutes, seconds);
    }
}
