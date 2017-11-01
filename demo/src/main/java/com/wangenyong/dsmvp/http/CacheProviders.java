package com.wangenyong.dsmvp.http;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;

/**
 *
 * @author wangenyong
 * @date 2017/11/1
 */

public interface CacheProviders {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<GankHttpResult> getGank(Observable<GankHttpResult> oGank, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
