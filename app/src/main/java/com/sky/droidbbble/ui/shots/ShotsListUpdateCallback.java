package com.sky.droidbbble.ui.shots;

import android.support.v7.util.ListUpdateCallback;

/**
 * Created by tonycheng on 2017/9/19.
 */

public abstract class ShotsListUpdateCallback implements ListUpdateCallback {

    abstract void onListChanged(boolean changed);

    @Override
    public void onInserted(int position, int count) {
    }

    @Override
    public void onRemoved(int position, int count) {
        onListChanged(true);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        onListChanged(true);
    }

    @Override
    public void onChanged(int position, int count, Object payload) {
        onListChanged(true);
    }
}
