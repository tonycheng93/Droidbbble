package com.sky.appcore.http;

import android.support.v4.BuildConfig;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
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

    public HttpMethod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(getServiceClazz());
    }

    public T getService() {
        return mService;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

//        ConnectionSpec connectionSpec = new ConnectionSpec
//                .Builder(ConnectionSpec.MODERN_TLS)
//                .tlsVersions(TlsVersion.TLS_1_2)
//                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
//                .allEnabledTlsVersions()
//                .supportsTlsExtensions(false)
//                .allEnabledCipherSuites()
//                .build();
//        builder.connectionSpecs(Collections.singletonList(connectionSpec));

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
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }
}
