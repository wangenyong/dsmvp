package com.wangenyong.dsmvp.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author wangenyong
 * @date 2017/5/25
 */

public class Api {
    private static ApiService SERVICE;

    private static final int DEFAULT_TIMEOUT = 15;

    public static ApiService getDefault() {
        if (SERVICE == null) {
            synchronized (Api.class) {
                OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
                httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

                SERVICE = new Retrofit.Builder()
                        .client(httpClientBuilder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(Url.BASE_URL)
                        .build().create(ApiService.class);
            }
        }
        return SERVICE;
    }
}
