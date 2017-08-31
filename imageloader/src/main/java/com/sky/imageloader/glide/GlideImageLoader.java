package com.sky.imageloader.glide;

import com.sky.imageloader.IImageLoader;

/**
 * Created by tonycheng on 2017/8/31.
 */

public class GlideImageLoader implements IImageLoader {

    private static final GlideImageLoader INSTANCE = new GlideImageLoader();

    public static GlideImageLoader getInstance() {
        return INSTANCE;
    }
}
