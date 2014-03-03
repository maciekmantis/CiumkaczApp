package com.mks.ciumkacz.stopwatch;

public class Result {

    private final long start;
    private final long time;


    public Result(long start, long time) {
        this.start = start;
        this.time = time;
    }

    public long getStart() {
        return start;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Result result = (Result) obj;
        return start == result.getStart() && time == result.getTime();
    }

    @Override
    public int hashCode() {
        final int hash = 111;
        return (int) (start * time) * hash;
    }

}
