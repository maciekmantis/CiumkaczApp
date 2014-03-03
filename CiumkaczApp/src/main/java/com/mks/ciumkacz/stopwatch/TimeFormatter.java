package com.mks.ciumkacz.stopwatch;

public class TimeFormatter {

    public static String format(long timeMillis) {
        int secs = (int) (timeMillis / 1000);
        int mins = secs / 60;
        int hours = mins / 60;
        secs = secs % 60;
        mins = mins % 60;
        return String.format("%02d:%02d:%02d", hours, mins, secs);
    }

}
