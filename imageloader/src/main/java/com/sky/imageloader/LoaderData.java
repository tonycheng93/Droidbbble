package com.sky.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by tonycheng on 2017/9/6.
 */

public class LoaderData {

    private Context mContext = null;

    private int mPlaceholderResId = 0;

    private Drawable mPlaceholderDrawable = null;

    private int mErrorPlaceholderResId = 0;

    private Drawable mErrorPlaceholderDrawable = null;

    private int mResourceId = 0;

    private Uri mUri = null;

    private int width = 0;

    private int height = 0;

    private ImageView.ScaleType mScaleType = null;

    private int radius = 0;

    private int roundCornerRadius = 0;

    private int blurRadius = 0;

    private boolean gray = false;

    private int colorFilter = 0;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public int getPlaceholderResId() {
        return mPlaceholderResId;
    }

    public void setPlaceholderResId(int placeholderResId) {
        mPlaceholderResId = placeholderResId;
    }

    public Drawable getPlaceholderDrawable() {
        return mPlaceholderDrawable;
    }

    public void setPlaceholderDrawable(Drawable placeholderDrawable) {
        mPlaceholderDrawable = placeholderDrawable;
    }

    public int getErrorPlaceholderResId() {
        return mErrorPlaceholderResId;
    }

    public void setErrorPlaceholderResId(int errorPlaceholderResId) {
        mErrorPlaceholderResId = errorPlaceholderResId;
    }

    public Drawable getErrorPlaceholderDrawable() {
        return mErrorPlaceholderDrawable;
    }

    public void setErrorPlaceholderDrawable(Drawable errorPlaceholderDrawable) {
        mErrorPlaceholderDrawable = errorPlaceholderDrawable;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        mResourceId = resourceId;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageView.ScaleType getScaleType() {
        return mScaleType;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        mScaleType = scaleType;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRoundCornerRadius() {
        return roundCornerRadius;
    }

    public void setRoundCornerRadius(int roundCornerRadius) {
        this.roundCornerRadius = roundCornerRadius;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public boolean isGray() {
        return gray;
    }

    public void setGray(boolean gray) {
        this.gray = gray;
    }

    public int getColorFilter() {
        return colorFilter;
    }

    public void setColorFilter(int colorFilter) {
        this.colorFilter = colorFilter;
    }
}
