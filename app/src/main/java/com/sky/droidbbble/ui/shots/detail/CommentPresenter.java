package com.sky.droidbbble.ui.shots.detail;

import com.sky.appcore.mvp.presenter.BasePresenter;
import com.sky.droidbbble.data.DataManager;
import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.utils.RxUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/22.
 */

public class CommentPresenter extends BasePresenter<ICommentView> {

    private Disposable mDisposable = null;

    @Override
    public void attachView(ICommentView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public void getComments(int id) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        DataManager.getInstance()
                .getComments(id)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        if (comments.isEmpty()) {
                            getMvpView().showEmpty();
                        } else {
                            getMvpView().showComments(comments);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError();
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
