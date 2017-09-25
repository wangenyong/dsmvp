package com.wangenyong.mvp.view;

/**
 * Created by wangenyong on 2017/5/22.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.wangenyong.mvp.bind.BindLayout;

import butterknife.ButterKnife;

/**
 * View的基类，处理所有有关View的操作
 */
public class ContentView {
    public View createView(Context context, @Nullable Bundle savedInstanceState) {
        return createView(LayoutInflater.from(context), savedInstanceState);
    }

    public View createView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        int layout = getClass().getAnnotation(BindLayout.class).value();
        View view = inflater.inflate(layout, null);
        ButterKnife.bind(this, view);
        return view;
    }
}

