package com.sky.droidbbble.ui.user;

import com.sky.appcore.mvp.view.MvpView;
import com.sky.droidbbble.data.model.User;

/**
 * Created by tonycheng on 2017/9/4.
 */

public interface IUserView extends MvpView {

    /**
     * display {@link User} information
     *
     * @param user {@link User}
     */
    void showUser(User user);
}
