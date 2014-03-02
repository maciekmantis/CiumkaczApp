package com.mks.ciumkacz.stopwatch;

public class Stopwatch {

    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        long now = System.currentTimeMillis();
        return now - start;
    }

    public long getStart() {
        return start;
    }

}
