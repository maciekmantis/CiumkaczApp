package com.mks.ciumkacz.controller;

import android.os.Handler;
import android.widget.TextView;

import com.mks.ciumkacz.R;
import com.mks.ciumkacz.stopwatch.Result;
import com.mks.ciumkacz.stopwatch.Stopwatch;
import com.mks.ciumkacz.stopwatch.TimeFormatter;

import java.util.ArrayList;
import java.util.List;

class StopwatchManager {

    private Stopwatch currentStopwatch;
    private Handler updateStopwatchHandler;
    private TextView resultField;

    List<Result> results;
    long resultsTime = 0L;

    private int state;

    private Runnable updateStopwatchTask = new Runnable() {
        @Override
        public void run() {
            resultField.setText(TimeFormatter.format(resultsTime + currentStopwatch.getElapsedTime()));
            updateStopwatchHandler.postDelayed(this, 0);
        }
    };

    public StopwatchManager(TextView resultField) {
        this.resultField = resultField;
        this.updateStopwatchHandler = new Handler();
        this.results = new ArrayList<Result>();
        this.state = StopwatchState.STOPPED;
    }

    public void start(Stopwatch stopwatch) {
        this.currentStopwatch = stopwatch;
        if (getState() == StopwatchState.STOPPED) {
            resetResultField();
        }
        updateStopwatchHandler.postDelayed(updateStopwatchTask, 0);
        setState(StopwatchState.STARTED);
    }

    public void pause() {
        if (getState() == StopwatchState.STARTED) {
            Result result = new Result(currentStopwatch.getStart(), currentStopwatch.getElapsedTime());
            results.add(result);
            resultsTime += result.getTime();
            updateStopwatchHandler.removeCallbacks(updateStopwatchTask);
            setState(StopwatchState.PAUSED);
        }
    }

    public void stop() {
        if (getState() == StopwatchState.STARTED) {
            Result result = new Result(currentStopwatch.getStart(), currentStopwatch.getElapsedTime());
            results.add(result);
            resultsTime += result.getTime();
            updateStopwatchHandler.removeCallbacks(updateStopwatchTask);
            setState(StopwatchState.STOPPED);
        } else if (getState() == StopwatchState.PAUSED) {
            setState(StopwatchState.STOPPED);
        }
    }

    private void resetResultField() {
        resultsTime = 0L;
        resultField.setText(R.string.stopwatch_time_elapsed_default);
    }

    private synchronized void setState(int state) {
        this.state = state;
    }

    public synchronized int getState() {
        return state;
    }

}
