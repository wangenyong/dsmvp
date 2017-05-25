package com.wangenyong.dsmvp;

import android.os.Bundle;

import com.wangenyong.mvp.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private MainView contentView = new MainView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView.createView(this, savedInstanceState));
    }
}
