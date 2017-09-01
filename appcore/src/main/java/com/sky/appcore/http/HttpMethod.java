package com.sky.appcore.http;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tonycheng on 2017/8/31.
 */

/**
 * must implements this class
 */

public abstract class HttpMethod {

    private static final int CONNECT_TIME_OUT = 10;
    private static final int READ_TIME_OUT = 10;
    private static final int WRITE_TIME_OUT = 10;

    private Map<String, Object> mServiceCache = new ArrayMap<>();

    @NonNull
    protected abstract String getBaseUrl();

    @SuppressWarnings("unchecked")
    public <T> T getService(@NonNull Class<T> service) {
        if (mServiceCache.containsKey(service.getName())) {
            return (T) mServiceCache.get(service.getName());
        } else {
            final T serviceClass = createService(service);
            mServiceCache.put(service.getName(), serviceClass);
            return serviceClass;
        }
    }

    private <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return (T) retrofit.create(serviceClass);
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpLoggingInterceptor());
        return builder.build();
    }
}
