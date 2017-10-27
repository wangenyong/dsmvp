package com.wangenyong.mvp.http;

/**
 *
 * @author wangenyong
 * @date 2017/5/23
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
