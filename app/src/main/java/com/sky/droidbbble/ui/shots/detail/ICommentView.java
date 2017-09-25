package com.sky.droidbbble.ui.shots.detail;

import com.sky.appcore.mvp.view.MvpView;
import com.sky.droidbbble.data.model.Comment;

import java.util.List;

/**
 * Created by tonycheng on 2017/9/22.
 */

public interface ICommentView extends MvpView {

    void showComments(List<Comment> commentList);

    void showEmpty();

    void showError();
}
