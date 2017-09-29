package com.sky.droidbbble.ui.user;

import com.sky.appcore.mvp.presenter.BasePresenter;
import com.sky.droidbbble.data.DataManager;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.utils.RxUtil;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
        RxUtil.dispose(mDisposable);
        DataManager.getInstance()
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onSuccess(User user) {
                        getMvpView().showUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e,"load user failed.");
                    }
                });
    }
}
