package com.sky.droidbbble.utils;

import android.support.v7.util.ListUpdateCallback;

/**
 * Created by tonycheng on 2017/9/19.
 */

/**
 * 这个类主要用来判断下拉刷新时，现有的数据源对比最新从服务器获取的数据源是否发生了变化，
 * 避免添加重复的数据。核心思想是使用 {@link android.support.v7.util.DiffUtil}
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
