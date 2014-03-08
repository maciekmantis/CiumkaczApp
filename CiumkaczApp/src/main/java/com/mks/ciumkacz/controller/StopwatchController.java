package com.mks.ciumkacz.controller;

import android.view.View;
import android.widget.Button;

import com.mks.ciumkacz.R;
import com.mks.ciumkacz.dummy.DummyContent;
import com.mks.ciumkacz.gui.StopwatchFragment;
import com.mks.ciumkacz.stopwatch.Stopwatch;

public class StopwatchController {

    private StopwatchFragment stopwatchFragment;
    private StopwatchManager stopwatchManager;

    public StopwatchController(StopwatchFragment stopwatchFragment) {
        this.stopwatchFragment = stopwatchFragment;
        this.stopwatchManager = new StopwatchManager(stopwatchFragment.getStopwatchTimeElapsedField());
    }

    public void action(View view, int action) {
        switch (action) {
            case StopwatchAction.ACTION_LEFT:
                startOrStop(view);
                break;
            case StopwatchAction.ACTION_RIGHT:
                startOrStop(view);
                break;
            case StopwatchAction.ACTION_PAUSE:
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
            DummyContent.ITEMS.add(new DummyContent.DummyItem("4", "Some txt"));
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