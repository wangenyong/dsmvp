package com.wangenyong.mvp.base;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by wangenyong on 2017/5/23.
 */

public class RxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
