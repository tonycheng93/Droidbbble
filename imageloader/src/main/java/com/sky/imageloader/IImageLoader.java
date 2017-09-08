package com.sky.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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
     * 指定转换为bitmap
     *
     * @return IImageLoader
     */
    IImageLoader asBitmap();
//
//    /**
//     * 指定转换为 drawable
//     *
//     * @return IImageLoader
//     */
//    IImageLoader asDrawable();
//
//    /**
//     * 指定转换为 gif
//     *
//     * @return IImageLoader
//     */
//    IImageLoader asGif();
//
//    /**
//     * 指定转换为 file
//     *
//     * @return IImageLoader
//     */
//    IImageLoader asFile();

    /**
     * 需要加载的图片资源地址
     *
     * @param model 图片来源，支持resourceId,url,Uri,file
     * @return IImageLoader
     */
    IImageLoader load(@NonNull Object model);

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
     * 设置图片圆角
     *
     * @param radius 圆角半径
     * @return IImageLoader
     */
    IImageLoader roundCorner(int radius);

    /**
     * 高斯模糊，传入的参数blurRadius为模糊度,越大就越模糊,实际效果要自己调试好相应的数字
     *
     * @param blurRadius 模糊度
     * @return IImageLoader
     */
    IImageLoader blur(int blurRadius);

    /**
     * 灰度处理
     *
     * @param needGray 是否需要灰度处理
     * @return IImageLoader
     */
    IImageLoader gray(boolean needGray);

    /**
     * 滤镜处理
     *
     * @param color 滤镜颜色
     * @return IImageLoader
     */
    IImageLoader colorFilter(int color);

    /**
     * 设置需要设置图片的目标 imageView {@link ImageView}
     *
     * @param imageView {@link ImageView}
     * @return IImageLoader
     */
    IImageLoader into(ImageView imageView);

    /**
     * 当且仅获取bitmap时使用
     *
     * @param callback {@link FinalCallback}
     * @return IImageLoader
     */
    IImageLoader into(FinalCallback callback);
}
