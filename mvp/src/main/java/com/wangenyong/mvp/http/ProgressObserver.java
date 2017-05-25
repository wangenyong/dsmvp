package com.wangenyong.mvp.http;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wangenyong on 2017/5/24.
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onCancelProgress() {

    }
}
