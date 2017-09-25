package com.wangenyong.dsmvp.view;

import android.util.Log;

import com.wangenyong.dsmvp.R;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;

import butterknife.OnClick;

/**
 * Created by wangenyong on 2017/9/25.
 */

@BindLayout(R.layout.fragment_android)
public class AndroidFragmentView extends ContentView {

    public void initView() {

    }

    @OnClick({R.id.btn_hello, R.id.btn_hi})
    public void hello() {
        Log.d("Android", "hello");
    }
}
