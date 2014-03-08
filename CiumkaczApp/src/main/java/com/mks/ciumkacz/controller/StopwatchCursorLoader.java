package com.mks.ciumkacz.controller;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

public class StopwatchCursorLoader extends CursorLoader {

    public StopwatchCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return super.loadInBackground();
        // TODO: return SQLiteCursor
    }
}
