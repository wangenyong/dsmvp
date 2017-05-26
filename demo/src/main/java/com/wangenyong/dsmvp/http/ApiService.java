package com.wangenyong.dsmvp.http;

import com.wangenyong.dsmvp.entity.Gank;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wangenyong on 2017/5/25.
 */

public interface ApiService {
    @GET("data/all/{num}/{page}")
    Observable<GankHttpResult<List<Gank>>> getGank(@Path("num") int num, @Path("page") int page);
}