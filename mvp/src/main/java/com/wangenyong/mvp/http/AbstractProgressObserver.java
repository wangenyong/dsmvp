package com.wangenyong.mvp.http;

import android.content.Context;

import com.wangenyong.mvp.view.SimpleLoadDialog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author wangenyong
 * @date 2017/5/24
 */

public abstract class AbstractProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    private SimpleLoadDialog dialogHandler;
    private Disposable mDisposable;
    private boolean isRefresh;
    private boolean isLoadMore;
    private String loadingInfo;

    public AbstractProgressObserver(Context context, boolean refresh, boolean loadMore, String info) {
        isRefresh = refresh;
        isLoadMore = loadMore;
        loadingInfo = info;
        dialogHandler = new SimpleLoadDialog(context, this, true);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        dismissProgressDialog();
        if (t instanceof ApiException) {
            onFail(t.getMessage());
        } else {
            onFail("请求失败，请稍后再试...");
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
    public void showProgressDialog() {
        if (!isRefresh && !isLoadMore && dialogHandler != null) {
            dialogHandler.show(loadingInfo);
        }
    }

    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.dismiss();
            dialogHandler = null;
        }
    }

    /**
     * 成功
     *
     * @param t JSON数据
     */
    protected abstract void onSuccess(T t);

    /**
     * 失败
     *
     * @param message 消息
     */
    protected abstract void onFail(String message);

}

