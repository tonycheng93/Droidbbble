package com.sky.dribbble.data.remote;

import com.sky.dribbble.data.model.Shots;
import com.sky.dribbble.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tonycheng on 2017/9/1.
 */

public interface DroidbbbleService {

    /**
     * get current user
     *
     * @return {@link User}
     */
    @GET("user")
    Observable<User> getUser();

    /**
     * get shots list
     *
     * @param perPage 每一页返回{@link Shots} 数目
     * @param page    页数
     * @return List<Shots>
     */
    @GET("shots")
    Observable<List<Shots>> getShots(@Query("per_page") int perPage, @Query("page") int page);
}
