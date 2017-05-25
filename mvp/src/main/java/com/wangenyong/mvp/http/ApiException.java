package com.wangenyong.mvp.http;

/**
 * Created by wangenyong on 2017/5/23.
 */

public class ApiException extends RuntimeException {
    protected String message;

    public ApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
