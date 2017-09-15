package com.sky.droidbbble.ui.shots;

import com.sky.appcore.mvp.view.MvpView;
import com.sky.droidbbble.data.model.Shots;

import java.util.List;

/**
 * Created by tonycheng on 2017/9/8.
 */

public interface IShotsView extends MvpView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 显示加载内容
     *
     * @param shotsList List<Shots>
     */
    void showShots(List<Shots> shotsList);

    /**
     * 未获取到内容
     */
    void showEmpty();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 加载出错时
     */
    void showError();
}
