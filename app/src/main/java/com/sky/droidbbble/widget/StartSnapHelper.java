package com.sky.droidbbble.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tonycheng on 2017/9/21.
 */

/**
 * 定位到第一个子View的SnapHelper
 */
public class StartSnapHelper extends LinearSnapHelper {
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];
        out[0] = 0;
        out[1] = ((VegaLayoutManager) layoutManager).getSnapHeight();
        return out;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        VegaLayoutManager customLayoutManager = (VegaLayoutManager) layoutManager;
        return customLayoutManager.findSnapView();
    }
}
