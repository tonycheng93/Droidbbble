package com.sky.imageloader;

import com.sky.imageloader.glide.GlideImageLoader;

/**
 * Created by tonycheng on 2017/8/31.
 */

public class ImageLoaderFactory {

    private static IImageLoader sImageLoader = null;

    public synchronized static IImageLoader getImageLoader() {
        if (sImageLoader == null) {
            sImageLoader = new GlideImageLoader();
        }
        return sImageLoader;
    }

    public synchronized static void destroy() {
        if (sImageLoader != null) {
            sImageLoader.destroy();
        }
        sImageLoader = null;
    }
}
