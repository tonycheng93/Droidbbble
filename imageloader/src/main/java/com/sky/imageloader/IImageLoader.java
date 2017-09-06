package com.sky.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by tonycheng on 2017/8/31.
 */

public interface IImageLoader {

    /**
     * 图片加载框架初始化设置
     *
     * @param context {@link Context}
     */
    void init(Context context);

    /**
     * 销毁操作，释放资源相关
     */
    void destroy();

    /**
     * 传入{@link Context},在需要加载图片时必须最先调用
     *
     * @param context {@link Context}
     * @return IImageLoader
     */
    IImageLoader with(Context context);

    /**
     * 设置占位图
     *
     * @param resourceId 资源id
     * @return IImageLoader
     */
    IImageLoader setPlaceholder(int resourceId);

    /**
     * 设置占位图
     *
     * @param drawable {@link Drawable}
     * @return IImageLoader
     */
    IImageLoader setPlaceholder(Drawable drawable);

    /**
     * 设置加载出错图片
     *
     * @param resourceId 资源id
     * @return IImageLoader
     */
    IImageLoader setErrorPlaceholder(int resourceId);

    /**
     * 设置加载出错图片
     *
     * @param drawable {@link Drawable}
     * @return IImageLoader
     */
    IImageLoader setErrorPlaceholder(Drawable drawable);

    /**
     * 设置加载图片的大小
     *
     * @param width  图片的宽度
     * @param height 图片的高度
     * @return IImageLoader
     */
    IImageLoader override(int width, int height);

    /**
     * 设置需要加载图片的Uri
     *
     * @param resourceId 资源id
     * @return IImageLoader
     */
    IImageLoader load(int resourceId);

    /**
     * 设置需要加载图片的Uri
     *
     * @param uri {@link Uri}
     * @return IImageLoader
     */
    IImageLoader load(Uri uri);

    /**
     * 设置需要显示的{@link android.widget.ImageView.ScaleType}
     *
     * @param scaleType {@link android.widget.ImageView.ScaleType}
     * @return IImageLoader
     */
    IImageLoader setScaleType(ImageView.ScaleType scaleType);

    /**
     * 设置圆形图片
     *
     * @param radius 圆的半径
     * @return IImageLoader
     */
    IImageLoader circle(int radius);

    /**
     * 设置需要设置图片的目标 imageView {@link ImageView}
     *
     * @param imageView {@link ImageView}
     * @return IImageLoader
     */
    IImageLoader into(ImageView imageView);
}
