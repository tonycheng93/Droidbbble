package com.sky.droidbbble.data;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.utils.NetworkUtil;
import com.sky.droidbbble.utils.RxUtil;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SyncService extends Service {

    private Disposable mDisposable;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Timber.i("Starting sync...");
        if (!NetworkUtil.isNetworkConnected(this)) {
            Timber.i("Sync canceled,connection not available");
            stopSelf(startId);
            return START_NOT_STICKY;
        }
        RxUtil.dispose(mDisposable);
        DataManager.getInstance().syncUser()
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onSuccess(User user) {
                        Timber.i("Sync successfully !");
                        stopSelf(startId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "error syncing.");
                        stopSelf(startId);
                    }
                });
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }
}
