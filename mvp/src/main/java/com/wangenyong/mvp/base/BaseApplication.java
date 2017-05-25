package com.wangenyong.mvp.base;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by wangenyong on 2017/5/24.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
