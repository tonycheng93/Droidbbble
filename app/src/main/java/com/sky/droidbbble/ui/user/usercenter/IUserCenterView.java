package com.sky.droidbbble.ui.user.usercenter;

import com.sky.appcore.mvp.view.MvpView;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;

import java.util.List;

/**
 * @author tonycheng
 * @date 2017/11/29
 */

public interface IUserCenterView extends MvpView {

    /**
     * Display user's information,such as followings' count
     * and followers' count.
     *
     * @param user {@link User}
     */
    void showUserInfo(User user);

    /**
     * Display the user has published shots.
     *
     * @param shots {@link List<Shots>}
     */
    void showUserShots(List<Shots> shots);
}
