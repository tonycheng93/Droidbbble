package com.sky.droidbbble.data.remote;

import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    Single<User> getUser();

    /**
     * get shots list
     *
     * @param perPage 每一页返回{@link Shots} 数目
     * @param page    页数
     * @return List<Shots>
     */
    @GET("shots")
    Observable<List<Shots>> getShots(@Query("per_page") int perPage, @Query("page") int page);

    /**
     * get comment list
     *
     * @param id shots id
     * @return Observable<List<Comment>>
     */
    @GET("shots/{id}/comments")
    Observable<List<Comment>> getComments(@Path("id") int id);
}
