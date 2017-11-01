package com.wangenyong.mvp.http;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author wangenyong
 * @date 2017/5/24
 */

public class HttpUtils {

    private HttpUtils() {
    }

    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    /**
     * 获取单例
     */
    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void toSubscribe(final Observable ob, final AbstractProgressObserver observer, RxAppCompatActivity activity) {
        Observable observable = ob
                .compose(RxHelper.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        observer.showProgressDialog();
                    }
                })
                .compose(activity.bindToLifecycle());

        observable.subscribe(observer);
    }

    public void toSubscribe(final Observable ob, final AbstractProgressObserver observer, RxFragment fragment) {
        final Observable observable = ob
                .compose(RxHelper.handleResult())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        observer.showProgressDialog();
                    }
                })
                .compose(fragment.bindToLifecycle());
        observable.subscribe(observer);
    }
}

