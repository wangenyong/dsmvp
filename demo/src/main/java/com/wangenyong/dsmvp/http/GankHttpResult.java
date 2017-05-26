package com.wangenyong.dsmvp.http;

import com.wangenyong.mvp.http.HttpResult;

/**
 * Created by wangenyong on 2017/5/25.
 */

public class GankHttpResult<T> extends HttpResult<T> {
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
