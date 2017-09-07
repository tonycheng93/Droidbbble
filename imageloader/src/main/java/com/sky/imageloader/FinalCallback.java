package com.sky.imageloader;

/**
 * Created by tonycheng on 2017/9/7.
 */

/**
 * 获取 Bitmap时的回调
 */
public interface FinalCallback {

    /**
     * 成功获取bitmap
     *
     * @param object
     */
    void onSuccess(Object object);

    /**
     * 获取bitmap失败
     *
     * @param throwable 异常信息
     */
    void onFailed(Throwable throwable);
}
