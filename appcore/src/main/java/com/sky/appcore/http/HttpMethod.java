package com.sky.appcore.http;

import com.google.gson.Gson;

import android.support.v4.BuildConfig;
import android.text.TextUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tonycheng on 2017/8/31.
 */

/**
 * must extends this class
 */

public abstract class HttpMethod<T> {

    private static final int CONNECT_TIME_OUT = 10;
    private static final int READ_TIME_OUT = 10;
    private static final int WRITE_TIME_OUT = 10;

    private T mService = null;

    protected abstract Class<T> getServiceClazz();

    protected abstract String getBaseUrl();

    protected abstract Map<String, String> getHeaders();

    //optional method
    protected int getConnectTimeOut() {
        return 0;
    }

    protected int getReadTimeOut() {
        return 0;
    }

    protected int getWriteTimeOut() {
        return 0;
    }

    protected OkHttpClient getOkHttpClient() {
        return null;
    }

    protected Gson getGson() {
        return null;
    }

    public HttpMethod() {
        final Retrofit.Builder builder = new Retrofit.Builder();
        if (!TextUtils.isEmpty(getBaseUrl())) {
            builder.baseUrl(getBaseUrl());
        } else {
            throw new IllegalArgumentException("base url can not be null.");
        }
        if (getOkHttpClient() != null) {
            builder.client(getOkHttpClient());
        } else {
            builder.client(createOkHttpClient());
        }
        if (getGson() != null) {
            builder.addConverterFactory(GsonConverterFactory.create(getGson()));
        } else {
            builder.addConverterFactory(GsonConverterFactory.create());
        }
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        mService = retrofit.create(getServiceClazz());
    }

    public T getService() {
        return mService;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (getConnectTimeOut() == 0) {
            builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        } else {
            builder.connectTimeout(getConnectTimeOut(), TimeUnit.SECONDS);
        }
        if (getReadTimeOut() == 0) {
            builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        } else {
            builder.readTimeout(getReadTimeOut(), TimeUnit.SECONDS);
        }
        if (getWriteTimeOut() == 0) {
            builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        } else {
            builder.writeTimeout(getWriteTimeOut(), TimeUnit.SECONDS);
        }
        builder.retryOnConnectionFailure(true);

        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(null, null, null);
            SSLSocketFactory noSSLv3Factory = new TlsOnlySocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultSSLSocketFactory(noSSLv3Factory);
            builder.sslSocketFactory(noSSLv3Factory);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        final Map<String, String> headers = getHeaders();
        if (headers != null) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        requestBuilder.addHeader(entry.getKey(), entry.getValue());
                    }
                    return chain.proceed(requestBuilder.build());
                }
            });
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}
