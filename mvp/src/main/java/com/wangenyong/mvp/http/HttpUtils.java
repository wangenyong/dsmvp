package com.wangenyong.mvp.http;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

    public void toSubscribe(final Observable ob, final ProgressObserver observer, String cacheKey, RxAppCompatActivity activity, boolean isSave, boolean forceRefresh) {
        Observable observable = ob
                .compose(RxHelper.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        observer.showProgressDialog();
                    }
                })
                .compose(activity.bindToLifecycle());

        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh)
                .subscribe(observer);
    }

    public void toSubscribe(final Observable ob, final ProgressObserver observer, String cacheKey, RxFragment fragment, boolean isSave, boolean forceRefresh) {
        final Observable observable = ob
                .compose(RxHelper.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        observer.showProgressDialog();
                    }
                })
                .compose(fragment.bindToLifecycle());
        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh)
                .subscribe(observer);
    }
}

