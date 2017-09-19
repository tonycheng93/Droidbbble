package com.sky.droidbbble.ui.shots;

import android.support.v7.util.ListUpdateCallback;

/**
 * Created by tonycheng on 2017/9/19.
 */

public abstract class ShotsListUpdateCallback implements ListUpdateCallback {

    abstract void onListChanged();

    @Override
    public void onInserted(int position, int count) {
    }

    @Override
    public void onRemoved(int position, int count) {
        onListChanged();
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        onListChanged();
    }

    @Override
    public void onChanged(int position, int count, Object payload) {
        onListChanged();
    }
}
