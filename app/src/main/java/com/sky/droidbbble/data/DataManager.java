package com.sky.droidbbble.data;

import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.http.DroidbbbleHttpMethod;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tonycheng on 2017/9/1.
 */

public class DataManager {

    private static final String TAG = "DataManager";

    private DataManager() {

    }

    private static class SingletonHolder {
        private static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Observable<User> getUser() {
        return DroidbbbleHttpMethod.getInstance()
                .getUser();
    }

    public Observable<List<Shots>> getShots(int perPage, int page) {
        return DroidbbbleHttpMethod.getInstance()
                .getShots(perPage, page);
    }

    public Observable<List<Comment>> getComments(int id) {
        return DroidbbbleHttpMethod.getInstance()
                .getComments(id);
    }
}
