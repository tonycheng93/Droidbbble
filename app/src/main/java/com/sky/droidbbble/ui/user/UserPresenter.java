package com.sky.droidbbble.ui.user;

import com.sky.appcore.mvp.presenter.BasePresenter;
import com.sky.droidbbble.data.DataManager;
import com.sky.droidbbble.data.model.User;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/4.
 */

public class UserPresenter extends BasePresenter<IUserView> {

    private static final String TAG = "UserPresenter";

    private Disposable mDisposable = null;

    @Override
    public void attachView(IUserView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public void getUser() {
        checkViewAttached();
        DataManager.getInstance()
                .getUser()
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Timber.d("onNext() current thread  = " + Thread.currentThread().getName());
                        getMvpView().showUser(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("onError current thread  = " + Thread.currentThread());
                        Timber.e("onError = " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
                    }
                });
    }
}
