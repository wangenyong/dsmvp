package com.wangenyong.dsmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ncapdevi.fragnav.FragNavController;
import com.wangenyong.dsmvp.view.AndroidFragment;
import com.wangenyong.dsmvp.view.GankFragment;
import com.wangenyong.mvp.base.BaseActivity;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wangenyong on 2017/5/25.
 */

@BindLayout(R.layout.activity_main)
public class MainView extends ContentView {
    @BindView(R.id.bottom_bar)
    BottomNavigationViewEx mNavigationViewEx;

    private FragNavController mFragNavController;

    public void initView(BaseActivity activity, @Nullable Bundle savedInstanceState) {
        mNavigationViewEx.enableAnimation(false);
        mNavigationViewEx.enableShiftingMode(false);
        mNavigationViewEx.enableItemShiftingMode(false);
        mNavigationViewEx.setTextVisibility(false);
        // set icon size
        final int iconSize = 32;
        mNavigationViewEx.setIconSize(iconSize, iconSize);
        // set item height
        mNavigationViewEx.setItemHeight(BottomNavigationViewEx.dp2px(activity, iconSize + 24));
        mNavigationViewEx.setIconsMarginTop(BottomNavigationViewEx.dp2px(activity, 12));

        List<Fragment> fragments = new ArrayList<>();

        GankFragment gankFragment = GankFragment.newInstance();

        AndroidFragment androidFragment = AndroidFragment.newInstance();

        DemoFragment userFragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", "User");
        userFragment.setArguments(bundle);

        DemoFragment otherFragment = new DemoFragment();
        bundle = new Bundle();
        bundle.putString("text", "Other");
        userFragment.setArguments(bundle);

        fragments.add(gankFragment);
        fragments.add(androidFragment);
        fragments.add(userFragment);
        fragments.add(otherFragment);

        FragNavController.Builder builder = FragNavController.newBuilder(savedInstanceState, activity.getSupportFragmentManager(), R.id.contentContainer);
        builder.rootFragments(fragments);
        mFragNavController = builder.build();

        mNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.gank) {
                    mFragNavController.switchTab(FragNavController.TAB1);
                    return true;
                } else if (item.getItemId() == R.id.android) {
                    mFragNavController.switchTab(FragNavController.TAB2);
                    return true;
                } else if (item.getItemId() == R.id.like) {
                    mFragNavController.switchTab(FragNavController.TAB3);
                    return true;
                } else if (item.getItemId() == R.id.user) {
                    mFragNavController.switchTab(FragNavController.TAB4);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        if (mFragNavController != null) {
            mFragNavController.onSaveInstanceState(outState);
        }
    }
}
