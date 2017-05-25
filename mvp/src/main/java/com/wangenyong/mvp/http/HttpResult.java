package com.wangenyong.mvp.http;

/**
 * Created by wangenyong on 2017/5/23.
 */

public abstract class HttpResult<T> {
    public abstract boolean successful();
    public abstract T getData();
    public abstract String getMessage();
}
