package com.wangenyong.dsmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.wangenyong.mvp.bind.BindId;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;

/**
 * Created by wangenyong on 2017/5/25.
 */

@BindLayout(R.layout.activity_main)
public class MainView extends ContentView {
    @BindId(R.id.bottom_bar)
    BottomNavigationViewEx mNavigationViewEx;

    public View createView(Context context, @Nullable Bundle savedInstanceState) {
        View view = super.createView(context, savedInstanceState);

        mNavigationViewEx.enableAnimation(false);
        mNavigationViewEx.enableShiftingMode(false);
        mNavigationViewEx.enableItemShiftingMode(false);
        mNavigationViewEx.setTextVisibility(false);
        // set icon size
        int iconSize = 32;
        mNavigationViewEx.setIconSize(iconSize, iconSize);
        // set item height
        mNavigationViewEx.setItemHeight(BottomNavigationViewEx.dp2px(context, iconSize + 24));
        mNavigationViewEx.setIconsMarginTop(BottomNavigationViewEx.dp2px(context, 12));
        return view;
    }
}
