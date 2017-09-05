package com.sky.dribbble.http;

import com.sky.dribbble.data.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by tonycheng on 2017/9/1.
 */

public interface DroidbbbleHttpService {

    /**
     * get current user
     *
     * @return {@link User}
     */
    @GET("user")
    Observable<User> getUser();
}
