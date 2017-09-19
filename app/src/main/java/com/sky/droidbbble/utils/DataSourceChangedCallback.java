package com.sky.droidbbble.utils;

import android.support.v7.util.ListUpdateCallback;

/**
 * Created by tonycheng on 2017/9/19.
 */

public abstract class DataSourceChangedCallback implements ListUpdateCallback {

    public abstract void onDataSourceChanged();

    @Override
    public void onInserted(int position, int count) {
    }

    @Override
    public void onRemoved(int position, int count) {
        onDataSourceChanged();
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        onDataSourceChanged();
    }

    @Override
    public void onChanged(int position, int count, Object payload) {
        onDataSourceChanged();
    }
}
