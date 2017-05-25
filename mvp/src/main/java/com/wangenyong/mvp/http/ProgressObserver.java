package com.wangenyong.mvp.http;

import android.content.Context;

import com.wangenyong.mvp.view.SimpleLoadDialog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wangenyong on 2017/5/24.
 */

public abstract class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    private SimpleLoadDialog dialogHandler;
    private Disposable mDisposable;

    public ProgressObserver(Context context) {
        dialogHandler = new SimpleLoadDialog(context, this, true);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        if (t instanceof ApiException) {
            _onError(t.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    /**
     * 显示Dialog
     */
    public void showProgressDialog(){
        if (dialogHandler != null) {
            dialogHandler.show();
        }
    }

    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog(){
        if (dialogHandler != null) {
            dialogHandler.dismiss();
            dialogHandler = null;
        }
    }

    protected abstract void _onNext(T t);
    protected abstract void _onError(String message);
}
