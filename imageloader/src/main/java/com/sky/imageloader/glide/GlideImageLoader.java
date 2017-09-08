package com.sky.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sky.imageloader.FinalCallback;
import com.sky.imageloader.IImageLoader;
import com.sky.imageloader.LoaderData;
import com.sky.imageloader.glide.transformations.BlurTransformation;
import com.sky.imageloader.glide.transformations.CircleTransformation;
import com.sky.imageloader.glide.transformations.ColorFilterTransformation;
import com.sky.imageloader.glide.transformations.CropCircleTransformation;
import com.sky.imageloader.glide.transformations.GrayscaleTransformation;
import com.sky.imageloader.glide.transformations.RoundCornerTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonycheng on 2017/8/31.
 */

public class GlideImageLoader implements IImageLoader {

    private static final String TAG = "GlideImageLoader";

    private LoaderData mLoaderData = null;
    private static final Object LOCK = new Object();

    @Override
    public void init(Context context) {

    }

    @Override
    public IImageLoader with(Context context) {
        synchronized (LOCK) {
            while (mLoaderData != null) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        mLoaderData = new LoaderData();
        mLoaderData.setContext(context);
        return this;
    }

    @Override
    public IImageLoader asBitmap() {
        mLoaderData.setAsBitmap(true);
        return this;
    }

    @Override
    public IImageLoader load(@NonNull Object model) {
        mLoaderData.setObject(model);
        return this;
    }

    @Override
    public IImageLoader setPlaceholder(int resourceId) {
        mLoaderData.setPlaceholderResId(resourceId);
        return this;
    }

    @Override
    public IImageLoader setPlaceholder(Drawable drawable) {
        mLoaderData.setPlaceholderDrawable(drawable);
        return this;
    }

    @Override
    public IImageLoader setErrorPlaceholder(int resourceId) {
        mLoaderData.setErrorPlaceholderResId(resourceId);
        return this;
    }

    @Override
    public IImageLoader setErrorPlaceholder(Drawable drawable) {
        mLoaderData.setErrorPlaceholderDrawable(drawable);
        return this;
    }

    @Override
    public IImageLoader override(int width, int height) {
        mLoaderData.setWidth(width);
        mLoaderData.setHeight(height);
        return this;
    }

    @Override
    public IImageLoader setScaleType(ImageView.ScaleType scaleType) {
        mLoaderData.setScaleType(scaleType);
        return this;
    }

    @Override
    public IImageLoader circle(int radius) {
        mLoaderData.setRadius(radius);
        return this;
    }

    @Override
    public IImageLoader roundCorner(int radius) {
        mLoaderData.setRoundCornerRadius(radius);
        return this;
    }

    @Override
    public IImageLoader blur(int blurRadius) {
        mLoaderData.setBlurRadius(blurRadius);
        return this;
    }

    @Override
    public IImageLoader gray(boolean needGray) {
        mLoaderData.setGray(needGray);
        return this;
    }

    @Override
    public IImageLoader colorFilter(int color) {
        mLoaderData.setColorFilter(color);
        return this;
    }

    @Override
    public IImageLoader into(ImageView imageView) {
        final GlideRequest<Drawable> glideRequest = GlideApp.with(mLoaderData.getContext()).load(mLoaderData.getObject());
        RequestOptions options = new RequestOptions();
        List<Transformation<Bitmap>> transformations = new ArrayList<>();

        //设置占位图
        if (mLoaderData.getPlaceholderResId() != 0) {
            options.placeholder(mLoaderData.getPlaceholderResId());
        }
        if (mLoaderData.getPlaceholderDrawable() != null) {
            options.placeholder(mLoaderData.getPlaceholderDrawable());
        }
        //设置加载出错占位图
        if (mLoaderData.getErrorPlaceholderResId() != 0) {
            options.error(mLoaderData.getErrorPlaceholderResId());
        }
        if (mLoaderData.getErrorPlaceholderDrawable() != null) {
            options.error(mLoaderData.getErrorPlaceholderDrawable());
        }
        //剪裁图片大小
        if (mLoaderData.getWidth() != 0 && mLoaderData.getHeight() != 0) {
            options.override(mLoaderData.getWidth(), mLoaderData.getHeight());
        }
        //设置图片ScaleType
        if (mLoaderData.getScaleType() != null) {
            switch (mLoaderData.getScaleType()) {
                case FIT_CENTER:
                    transformations.add(new FitCenter());
                    break;
                case CENTER_CROP:
                    transformations.add(new CenterCrop());
                    break;
                case CENTER_INSIDE:
                    transformations.add(new CenterInside());
                    break;
                default:
                    transformations.add(new CenterCrop());
                    break;
            }
        }
        //设置灰度
        if (mLoaderData.isGray()) {
            transformations.add(new GrayscaleTransformation(mLoaderData.getContext()));
        }
        //设置高斯模糊
        if (mLoaderData.getBlurRadius() != 0) {
            transformations.add(new BlurTransformation(mLoaderData.getContext(), mLoaderData
                    .getBlurRadius()));
        }
        //设置滤镜
        if (mLoaderData.getColorFilter() != 0) {
            transformations.add(new ColorFilterTransformation(mLoaderData.getContext(),
                    mLoaderData.getColorFilter()));
        }
        //设置图片圆角
        if (mLoaderData.getRoundCornerRadius() != 0) {
            transformations.add(new RoundCornerTransform(mLoaderData.getRoundCornerRadius()));
        }
        //设置圆形图片
        if (mLoaderData.getRadius() != 0) {
            transformations.add(new CircleTransformation(mLoaderData.getRadius()));
        }

        //note: if transformations is empty,then program will throw
        //java.lang.IllegalArgumentException:
        // MultiTransformation must contain at least one Transformation

        if (!transformations.isEmpty()) {
            options.transform(new MultiTransformation<>(transformations));
        }
        glideRequest.apply(options);
        glideRequest.into(imageView);
        return this;
    }

    @Override
    public IImageLoader into(@NonNull final FinalCallback callback) {
        GlideRequests glideRequests = GlideApp.with(mLoaderData.getContext());
        GlideRequest glideRequest;
        if (mLoaderData.isAsBitmap()) {
            glideRequest = glideRequests.asBitmap().load(mLoaderData.getObject());
        } else {
            glideRequest = glideRequests.load(mLoaderData.getObject());
        }

        RequestOptions options = new RequestOptions();
        List<Transformation<Bitmap>> transformations = new ArrayList<>();

        //设置占位图
        if (mLoaderData.getPlaceholderResId() != 0) {
            options.placeholder(mLoaderData.getPlaceholderResId());
        }
        if (mLoaderData.getPlaceholderDrawable() != null) {
            options.placeholder(mLoaderData.getPlaceholderDrawable());
        }
        //设置加载出错占位图
        if (mLoaderData.getErrorPlaceholderResId() != 0) {
            options.error(mLoaderData.getErrorPlaceholderResId());
        }
        if (mLoaderData.getErrorPlaceholderDrawable() != null) {
            options.error(mLoaderData.getErrorPlaceholderDrawable());
        }
        //剪裁图片大小
        if (mLoaderData.getWidth() != 0 && mLoaderData.getHeight() != 0) {
            options.override(mLoaderData.getWidth(), mLoaderData.getHeight());
        }
        //设置图片ScaleType
        if (mLoaderData.getScaleType() != null) {
            switch (mLoaderData.getScaleType()) {
                case FIT_CENTER:
                    transformations.add(new FitCenter());
                    break;
                case CENTER_CROP:
                    transformations.add(new CenterCrop());
                    break;
                case CENTER_INSIDE:
                    transformations.add(new CenterInside());
                    break;
                default:
                    transformations.add(new CenterCrop());
                    break;
            }
        }
        //设置灰度
        if (mLoaderData.isGray()) {
            transformations.add(new GrayscaleTransformation(mLoaderData.getContext()));
        }
        //设置高斯模糊
        if (mLoaderData.getBlurRadius() != 0) {
            transformations.add(new BlurTransformation(mLoaderData.getContext(), mLoaderData
                    .getBlurRadius()));
        }
        //设置滤镜
        if (mLoaderData.getColorFilter() != 0) {
            transformations.add(new ColorFilterTransformation(mLoaderData.getContext(),
                    mLoaderData.getColorFilter()));
        }
        //设置图片圆角
        if (mLoaderData.getRoundCornerRadius() != 0) {
            transformations.add(new RoundCornerTransform(mLoaderData.getRoundCornerRadius()));
        }
        //设置圆形图片
        if (mLoaderData.getRadius() != 0) {
            transformations.add(new CropCircleTransformation(mLoaderData.getContext()));
        }

        //note: if transformations is empty,then program will throw
        //java.lang.IllegalArgumentException:
        // MultiTransformation must contain at least one Transformation
        if (!transformations.isEmpty()) {
            options.transform(new MultiTransformation<>(transformations));
        }
        glideRequest.apply(options);
        glideRequest.into(new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (resource != null) {
                    callback.onSuccess(resource);
                } else {
                    callback.onFailed(new Throwable("bitmap == null or callback " +
                            "== null"));
                }
            }
        });
        return this;
    }

    @Override
    public void destroy() {
        notifyThread();

    }

    private void notifyThread() {
        if (mLoaderData != null) {
            mLoaderData = null;
            synchronized (LOCK) {
                LOCK.notify();
            }
        }
    }
}
