package com.sky.droidbbble;

import android.app.Application;
import android.widget.TabHost;

import com.bumptech.glide.Glide;
import com.sky.dribbble.BuildConfig;
import com.sky.droidbbble.utils.FontsManager;
import com.sky.imageloader.glide.GlideApp;
import com.sky.imageloader.glide.transformations.RoundCornerTransform;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 项目名称：Dribbble
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2017/9/2 16:44
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DroidbbbleApp extends Application {

    private static final String FONT_PATH = "fonts/Myriad-Pro-Light-Italic.otf";

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(base);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
//        Fabric.with(this, new Crashlytics());

        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        FontsManager.initFromAssets(getApplicationContext(), FONT_PATH);
                    }
                });
    }
}
