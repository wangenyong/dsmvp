package com.wangenyong.mvp.http;

import android.content.Context;

import com.google.gson.stream.MalformedJsonException;
import com.wangenyong.mvp.view.SimpleLoadDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.rx_cache2.RxCacheException;

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
    private final String SEPARATOR = "#";

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

        if (t instanceof CompositeException) {
            CompositeException compositeException = (CompositeException) t;
            for (Throwable throwable : compositeException.getExceptions()) {
                if (throwable instanceof SocketTimeoutException) {
                    onFail(ApiException.CODE_TIMEOUT, ApiException.SOCKET_TIMEOUT_EXCEPTION);
                } else if (throwable instanceof ConnectException) {
                    onFail(ApiException.CODE_UNCONNECTED, ApiException.CONNECT_EXCEPTION);
                } else if (throwable instanceof UnknownHostException) {
                    onFail(ApiException.CODE_UNCONNECTED, ApiException.CONNECT_EXCEPTION);
                } else if (throwable instanceof RxCacheException) {
                    //缓存异常暂时不做处理
                } else if (throwable instanceof MalformedJsonException) {
                    onFail(ApiException.CODE_MALFORMED_JSON, ApiException.MALFORMED_JSON_EXCEPTION);
                }
            }
        } else {
            String msg = t.getMessage();
            int code;
            if (msg.contains(SEPARATOR)) {
                code = Integer.parseInt(msg.split("#")[0]);
                onFail(code, msg.split("#")[1]);
            } else {
                code = ApiException.CODE_DEFAULT;
                onFail(code, msg);
            }
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
     *
     * 失败
     * @param code
     * @param message
     */
    protected abstract void onFail(int code, String message);

}

