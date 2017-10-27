package com.wangenyong.dsmvp.http;

import com.wangenyong.mvp.http.AbstractHttpResult;

/**
 *
 * @author wangenyong
 * @date 2017/5/25
 */

public class GankHttpResult<T> extends AbstractHttpResult<T> {
    boolean error;
    String message = "";

    T results;

    @Override
    public boolean successful() {
        return !error;
    }

    @Override
    public T getData() {
        return results;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
