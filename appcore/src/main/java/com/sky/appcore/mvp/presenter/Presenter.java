package com.sky.appcore.mvp.presenter;

/**
 * Created by tonycheng on 2017/8/31.
 */

import com.sky.appcore.mvp.view.MvpView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
