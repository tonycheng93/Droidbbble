package com.sky.droidbbble.data;

import com.sky.droidbbble.DroidbbbleApp;
import com.sky.droidbbble.data.local.DatabaseHelper;
import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.http.DroidbbbleHttpMethod;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by tonycheng on 2017/9/1.
 */

public class DataManager {

    private static final String TAG = "DataManager";

    private final DatabaseHelper mDatabaseHelper;

    private DataManager() {
        mDatabaseHelper = new DatabaseHelper(DroidbbbleApp.applicationContext);
    }

    private static class SingletonHolder {
        private static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Single<User> syncUser() {
        return DroidbbbleHttpMethod.getInstance()
                .getUser()
                .flatMap(new Function<User, SingleSource<? extends User>>() {
                    @Override
                    public SingleSource<? extends User> apply(User user) throws Exception {
                        return mDatabaseHelper.saveUser(user);
                    }
                });
    }

    public Single<User> getUser() {
        return mDatabaseHelper.getUser();
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
