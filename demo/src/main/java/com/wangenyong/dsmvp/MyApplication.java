package com.wangenyong.dsmvp;

import com.wangenyong.dsmvp.http.Repository;
import com.wangenyong.mvp.base.BaseApplication;

/**
 *
 * @author wangenyong
 * @date 2017/5/25
 */

public class MyApplication extends BaseApplication {
    private Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mRepository = Repository.init(getFilesDir());
    }

    public Repository getRepository() {
        return mRepository;
    }
}
