package com.sky.dribbble.data;

import com.sky.dribbble.data.model.User;
import com.sky.dribbble.http.DribbbleHttpMethod;

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
        return DribbbleHttpMethod.getInstance()
                .getUser();
    }
}
