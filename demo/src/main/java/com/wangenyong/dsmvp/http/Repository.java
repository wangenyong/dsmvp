package com.wangenyong.dsmvp.http;

import android.app.Activity;

import com.trello.rxlifecycle2.components.RxFragment;
import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.dsmvp.view.GankFragment;
import com.wangenyong.mvp.http.AbstractProgressObserver;
import com.wangenyong.mvp.http.HttpUtils;
import com.wangenyong.mvp.http.RxHelper;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author wangenyong
 * @date 2017/11/1
 */

public class Repository {
    public static final int PER_PAGE = 20;

    public static Repository init(File cacheDir) {
        return new Repository(cacheDir);
    }

    private final CacheProviders cacheProviders;
    private final RestApi restApi;

    public Repository(File cacheDir) {
        cacheProviders = new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())
                .using(CacheProviders.class);

        restApi = new Retrofit.Builder()
                .baseUrl(RestApi.URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestApi.class);
    }

    public void getGankWithCache(AbstractProgressObserver<List<Gank>> observer, final int page, final GankFragment fragment, final boolean update) {
        HttpUtils.getInstance().toSubscribe(cacheProviders.getGank(restApi.getGank(PER_PAGE, page), new DynamicKey(page), new EvictProvider(update)), observer, fragment);
    }

    public void getGank(AbstractProgressObserver<List<Gank>> observer, final int page, final GankFragment fragment) {
        HttpUtils.getInstance().toSubscribe(restApi.getGank(PER_PAGE, page), observer, fragment);
    }


}
