package com.sky.imageloader;

import com.sky.imageloader.glide.GlideImageLoader;

/**
 * Created by tonycheng on 2017/8/31.
 */

public class ImageLoaderFactory {

    public static IImageLoader getImageLoader() {
        return GlideImageLoader.getInstance();
    }
}
