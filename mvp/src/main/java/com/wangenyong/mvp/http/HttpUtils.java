package com.wangenyong.mvp.http;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;

/**
 * Created by wangenyong on 2017/5/24.
 */

public class HttpUtils {

    private HttpUtils() {}

    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    /**
     * 获取单例
     */
    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void toSubscribe(Observable ob, final ProgressObserver observer, String cacheKey, RxAppCompatActivity activity, boolean isSave, boolean forceRefresh) {
        Observable observable = ob
                .compose(RxHelper.handleResult())
                .compose(activity.bindToLifecycle());

        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh)
                .subscribe(observer);
    }

    public void toSubscribe(Observable ob, final ProgressObserver observer, String cacheKey, RxFragment fragment, boolean isSave, boolean forceRefresh) {
        Observable observable = ob
                .compose(RxHelper.handleResult())
                .compose(fragment.bindToLifecycle());
        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh)
                .subscribe(observer);
    }
}

