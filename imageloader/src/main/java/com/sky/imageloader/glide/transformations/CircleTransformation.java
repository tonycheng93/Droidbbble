package com.sky.imageloader.glide.transformations;

/**
 * Created by tonycheng on 2017/9/6.
 */

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * 实现圆形图片
 */
public class CircleTransformation extends BitmapTransformation {

    private static final int VERSION = 1;
    private static final String ID = "com.sky.imageloader.glide.transformations.circletransform." + VERSION;
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int radius;

    public CircleTransformation(int radius) {
        this.radius = radius;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.circleCrop(pool, toTransform, radius, radius);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CircleTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
