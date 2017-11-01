package com.wangenyong.dsmvp.http;

import com.wangenyong.dsmvp.entity.Gank;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author wangenyong
 * @date 2017/11/1
 */

public interface RestApi {
    String URL_BASE = "http://gank.io/api/";

    /**
     * 获取干货数据
     * @param num 每页的数量
     * @param page 页码
     * @return
     */
    @GET("data/all/{num}/{page}")
    Observable<GankHttpResult> getGank(@Path("num") int num, @Path("page") int page);
}
