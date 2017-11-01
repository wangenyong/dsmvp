package com.wangenyong.mvp.http;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wangenyong
 * @date 2017/5/23
 */

public class RxHelper {

    /**
     * 对网络数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<AbstractHttpResult<T>, T> handleResult() {
        return new ObservableTransformer<AbstractHttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<AbstractHttpResult<T>> upstream) {
                return upstream.flatMap(new Function<AbstractHttpResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull AbstractHttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.successful()) {
                            return createData(tHttpResult.getData());
                        } else {
                            return Observable.error(new ApiException(tHttpResult.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(data);
                emitter.onComplete();
            }
        });
    }
}
