package com.mks.ciumkacz.stopwatch;

import android.os.SystemClock;

public class Stopwatch {

    private final long start;

    public Stopwatch() {
        start = systemCurrentTime();
    }

    public long getElapsedTime() {
        long now = systemCurrentTime();
        return now - start;
    }

    public long getStart() {
        return start;
    }

    private long systemCurrentTime() {
        return SystemClock.uptimeMillis();
    }

}
