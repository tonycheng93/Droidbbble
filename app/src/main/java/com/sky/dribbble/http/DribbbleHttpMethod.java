package com.sky.dribbble.http;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.sky.appcore.http.HttpMethod;

import java.util.Map;

/**
 * Created by tonycheng on 2017/9/1.
 */

public class DribbbleHttpMethod extends HttpMethod<DribbbleHttpService> {

    @NonNull
    @Override
    protected Class<DribbbleHttpService> getServiceClazz() {
        return DribbbleHttpService.class;
    }

    @NonNull
    @Override
    protected String getBaseUrl() {
        return null;
    }

    @Override
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new ArrayMap<>();
        headers.put("Authorization", "Bearer 4bedf7d503cec5b96a2f10a2d4bfac414a9e33353849cdd39bbcb99ab2b526d8");
        return headers;
    }
}
