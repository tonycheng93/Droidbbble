package com.sky.dribbble.http;

import android.support.v4.util.ArrayMap;

import com.sky.appcore.http.HttpMethod;
import com.sky.dribbble.data.model.User;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tonycheng on 2017/9/1.
 */

public class DroidbbbleHttpMethod extends HttpMethod<DroidbbbleHttpService> {

    private DroidbbbleHttpMethod() {
    }

    private static class SingletonHolder {
        private static final DroidbbbleHttpMethod INSTANCE = new DroidbbbleHttpMethod();
    }

    public static DroidbbbleHttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    protected Class<DroidbbbleHttpService> getServiceClazz() {
        return DroidbbbleHttpService.class;
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.dribbble.com/v1/";
    }

    @Override
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new ArrayMap<>();
        headers.put("Authorization", "Bearer 4bedf7d503cec5b96a2f10a2d4bfac414a9e33353849cdd39bbcb99ab2b526d8");
        return headers;
    }

    public Observable<User> getUser() {
        return getService().getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
