package com.sky.droidbbble.ui.shots;


import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.utils.DiffUtil;

import java.util.List;

import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/18.
 */

public class ShotsDiffCallback extends DiffUtil.Callback {

    private final List<Shots> oldList;

    private final List<Shots> newList;

    public ShotsDiffCallback(List<Shots> oldList, List<Shots> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        boolean is = oldList.get(oldItemPosition).getId()
                == newList.get(newItemPosition).getId();
        Timber.d("areItemsTheSame = " + is);
        return is;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final String oldTitle = oldList.get(oldItemPosition).getTitle();
        final String newTitle = newList.get(newItemPosition).getTitle();
        boolean is = oldTitle.equals(newTitle);
        Timber.d("areContentsTheSame = " + is);
        return is;
    }
}
