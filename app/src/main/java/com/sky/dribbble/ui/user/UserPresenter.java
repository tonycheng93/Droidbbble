package com.sky.dribbble.ui.user;

import com.sky.appcore.mvp.presenter.BasePresenter;
import com.sky.dribbble.data.DataManager;
import com.sky.dribbble.data.model.User;

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
        Timber.d("getUser");
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
                        getMvpView().showUser(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("onError = " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
                    }
                });
    }
}