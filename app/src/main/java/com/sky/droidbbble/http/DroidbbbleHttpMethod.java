package com.sky.droidbbble.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.support.v4.util.ArrayMap;

import com.sky.appcore.http.HttpMethod;
import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.data.remote.DroidbbbleService;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tonycheng on 2017/9/1.
 */

public class DroidbbbleHttpMethod extends HttpMethod<DroidbbbleService> {

    private static final String ACCESS_TOAKEN =
            "Bearer 4bedf7d503cec5b96a2f10a2d4bfac414a9e33353849cdd39bbcb99ab2b526d8";

    private DroidbbbleHttpMethod() {
    }

    private static class SingletonHolder {
        private static final DroidbbbleHttpMethod INSTANCE = new DroidbbbleHttpMethod();
    }

    public static DroidbbbleHttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    protected Class<DroidbbbleService> getServiceClazz() {
        return DroidbbbleService.class;
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.dribbble.com/v1/";
    }

    @Override
    protected Gson getGson() {
//        2017-09-20T15:00:35Z YYYY-MM-DDTHH:MM:SSZ
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
    }

    @Override
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new ArrayMap<>();
        headers.put("Authorization", ACCESS_TOAKEN);
        return headers;
    }

    /**
     * get current user
     */
    public Single<User> getUser() {
        return getService().getUser()
                .subscribeOn(Schedulers.io());
    }

    /**
     * get shots list
     *
     * @param perPage perPage shots list count
     * @param page    page
     */
    public Observable<List<Shots>> getShots(int perPage, int page) {
        return getService().getShots(perPage, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get shots' comments
     *
     * @param id shots id
     */
    public Observable<List<Comment>> getComments(int id) {
        return getService().getComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
