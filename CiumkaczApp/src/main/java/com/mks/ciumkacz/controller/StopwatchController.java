package com.mks.ciumkacz.controller;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mks.ciumkacz.R;
import com.mks.ciumkacz.gui.StopwatchFragment;
import com.mks.ciumkacz.stopwatch.Result;
import com.mks.ciumkacz.stopwatch.Stopwatch;
import com.mks.ciumkacz.stopwatch.TimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class StopwatchController {

    public static final int ACTION_LEFT = 0;
    public static final int ACTION_PAUSE = 1;
    public static final int ACTION_RIGHT = 2;

    private StopwatchFragment stopwatchFragment;
    private StopwatchManager stopwatchManager;

    public StopwatchController(StopwatchFragment stopwatchFragment) {
        this.stopwatchFragment = stopwatchFragment;
        this.stopwatchManager = new StopwatchManager(stopwatchFragment.getStopwatchTimeElapsedField());
    }

    public void action(View view, int action) {
        switch (action) {
            case ACTION_LEFT:
                startOrStop(view);
                break;
            case ACTION_RIGHT:
                startOrStop(view);
                break;
            case ACTION_PAUSE:
                pauseOrStart(view);
                break;
            default:
                throw new RuntimeException("No action found");
        }

    }

    private void startOrStop(View view) {
        if (stopwatchManager.getState() == StopwatchState.STOPPED) {
            stopwatchManager.start(new Stopwatch());
            if (view.getClass() == Button.class) {
                ((Button)view).setText(R.string.stopwatch_stop);
            }
            if (view.getId() == R.id.stopwatch_start_left) {
                stopwatchFragment.getStopwatchStartRight().setEnabled(false);
            } else if (view.getId() == R.id.stopwatch_start_right) {
                stopwatchFragment.getStopwatchStartLeft().setEnabled(false);
            }
        } else {
            stopwatchManager.stop();
            if (view.getId() == R.id.stopwatch_start_left) {
                stopwatchFragment.getStopwatchStartLeft().setText(R.string.stopwatch_start_left);
                stopwatchFragment.getStopwatchStartRight().setEnabled(true);
            } else if (view.getId() == R.id.stopwatch_start_right) {
                stopwatchFragment.getStopwatchStartRight().setText(R.string.stopwatch_start_right);
                stopwatchFragment.getStopwatchStartLeft().setEnabled(true);
            }
        }
    }

    private void pauseOrStart(View view) {
        if (stopwatchManager.getState() == StopwatchState.STARTED) {
            stopwatchManager.pause();
        } else if (stopwatchManager.getState() == StopwatchState.PAUSED) {
            stopwatchManager.start(new Stopwatch());
        }
    }

}

class StopwatchManager {

    private Stopwatch currentStopwatch;
    private Handler updateStopwatchHandler;
    private TextView resultField;

    List<Result> results;
    long resultsTime = 0L;

    private StopwatchState state;

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

    private synchronized void setState(StopwatchState state) {
        this.state = state;
    }

    public synchronized StopwatchState getState() {
        return state;
    }

}

enum StopwatchState {STARTED, PAUSED, STOPPED}