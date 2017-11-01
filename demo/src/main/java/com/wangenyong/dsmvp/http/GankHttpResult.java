package com.wangenyong.dsmvp.http;

import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.mvp.http.AbstractHttpResult;

import java.util.List;

/**
 *
 * @author wangenyong
 * @date 2017/5/25
 */

public class GankHttpResult extends AbstractHttpResult<List<Gank>> {
    boolean error;
    String message = "";

    List<Gank> results;

    @Override
    public boolean successful() {
        return !error;
    }

    @Override
    public List<Gank> getData() {
        return results;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
