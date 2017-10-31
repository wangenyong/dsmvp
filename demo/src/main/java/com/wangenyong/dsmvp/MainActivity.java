package com.wangenyong.dsmvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ncapdevi.fragnav.FragNavController;
import com.wangenyong.dsmvp.databinding.ActivityMainBinding;
import com.wangenyong.dsmvp.view.AndroidFragment;
import com.wangenyong.dsmvp.view.GankFragment;
import com.wangenyong.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * @author wangenyong
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private FragNavController mFragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void initView(@Nullable Bundle savedInstanceState) {
        mBinding.bottomBar.enableAnimation(false);
        mBinding.bottomBar.enableShiftingMode(false);
        mBinding.bottomBar.enableItemShiftingMode(false);
        mBinding.bottomBar.setTextVisibility(false);
        // set icon size
        final int iconSize = 32;
        mBinding.bottomBar.setIconSize(iconSize, iconSize);
        // set item height
        mBinding.bottomBar.setItemHeight(BottomNavigationViewEx.dp2px(this, iconSize + 24));
        mBinding.bottomBar.setIconsMarginTop(BottomNavigationViewEx.dp2px(this, 12));

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
        otherFragment.setArguments(bundle);

        fragments.add(gankFragment);
        fragments.add(androidFragment);
        fragments.add(userFragment);
        fragments.add(otherFragment);

        FragNavController.Builder builder = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.contentContainer);
        builder.rootFragments(fragments);
        mFragNavController = builder.build();

        mBinding.bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
}
