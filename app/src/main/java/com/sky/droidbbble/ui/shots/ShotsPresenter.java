package com.sky.droidbbble.ui.shots;

import android.support.v7.util.DiffUtil;

import com.sky.appcore.mvp.presenter.BasePresenter;
import com.sky.droidbbble.data.DataManager;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.utils.DataSourceChangedCallback;
import com.sky.droidbbble.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/8.
 */

public class ShotsPresenter extends BasePresenter<IShotsView> {

    private Disposable mDisposable = null;
    /**
     * 这个集合是用来缓存上一次下拉刷新时的数据，增加一个这个集合的目的是：
     * 避免重复的数据，因为下拉刷新时存在服务器数据并未更新情况，此时不应该通知
     * Adapter数据源发生变化。
     */
    private List<Shots> mCachedShotsList = new ArrayList<>();

    /**
     * 是否第一次加载数据，此时是不需要进行数据源对比，避免浪费性能
     */
    private boolean isFirstAddShots = true;

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

    public void getShots(int perPage, final int page) {
        Timber.d("page = " + page);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        if (page == 1) {
            getMvpView().showLoading();
        }
        DataManager.getInstance().getShots(perPage, page)
                .subscribe(new Observer<List<Shots>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull final List<Shots> shotsList) {
                        getMvpView().hideLoading();
                        if (page == 1) {
                            if (isFirstAddShots) {
                                isFirstAddShots = false;
                                mCachedShotsList = shotsList;
                                if (shotsList.isEmpty()) {
                                    getMvpView().showEmpty();
                                } else {
                                    getMvpView().showShots(shotsList);
                                }
                            } else {
                                final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                                        new ShotsDiffCallback(mCachedShotsList, shotsList), false);
                                diffResult.dispatchUpdatesTo(new DataSourceChangedCallback() {
                                    @Override
                                    public void onDataSourceChanged() {
                                        mCachedShotsList = shotsList;
                                        if (shotsList.isEmpty()) {
                                            getMvpView().showEmpty();
                                        } else {
                                            getMvpView().showShots(shotsList);
                                        }
                                    }
                                });
                            }
                        } else {
                            if (shotsList.isEmpty()) {
                                getMvpView().showEmpty();
                            } else {
                                getMvpView().showShots(shotsList);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                        getMvpView().hideLoading();
                        getMvpView().showError();
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
                    }
                });
    }
}
