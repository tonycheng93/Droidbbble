package com.sky.droidbbble.ui.shots;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.sky.droidbbble.data.model.Shots;

import java.util.List;

/**
 * Created by tonycheng on 2017/9/19.
 */

public class ShotsDiffCallback extends DiffUtil.Callback {

    private final List<Shots> oldList;
    private final List<Shots> newList;

    public ShotsDiffCallback(@NonNull List<Shots> oldList, @NonNull List<Shots> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()
                == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
