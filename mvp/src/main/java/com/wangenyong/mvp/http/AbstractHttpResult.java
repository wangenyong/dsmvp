package com.wangenyong.mvp.http;

/**
 * @author wangenyong
 * @date 2017/5/23
 */

public abstract class AbstractHttpResult<T> {

    /**
     * 判断返回JSON是否正确
     *
     * @return 返回结果
     */
    public abstract boolean successful();

    /**
     * 获取返回的JSON数据
     *
     * @return JSON数据
     */
    public abstract T getData();

    /**
     * 获取返回的消息
     *
     * @return 消息字符串
     */
    public abstract String getMessage();
}
