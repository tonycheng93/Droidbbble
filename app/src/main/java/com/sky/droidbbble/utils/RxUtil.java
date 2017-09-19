package com.sky.droidbbble.utils;

import io.reactivex.disposables.Disposable;

/**
 * Created by tonycheng on 2017/9/19.
 */

public class RxUtil {

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
