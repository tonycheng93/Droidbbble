package com.sky.dribbble.ui.main.shots;

import com.sky.appcore.mvp.presenter.BasePresenter;
import com.sky.dribbble.data.DataManager;
import com.sky.dribbble.data.model.Shots;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by tonycheng on 2017/9/8.
 */

public class ShotsPresenter extends BasePresenter<IShotsView> {

    private static final String TAG = "ShotsPresenter";

    private Disposable mDisposable = null;

    @Override
    public void attachView(IShotsView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public void getShots(int perPage, int page) {
        checkViewAttached();
        getMvpView().showLoading();
        DataManager.getInstance().getShots(perPage, page)
                .subscribe(new Observer<List<Shots>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Shots> shotsList) {
                        getMvpView().hideLoading();
                        if (shotsList.isEmpty()) {
                            getMvpView().showEmpty();
                        } else {
                            getMvpView().showShots(shotsList);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
