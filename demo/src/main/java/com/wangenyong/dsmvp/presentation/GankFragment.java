package com.wangenyong.dsmvp.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.dsmvp.http.Api;
import com.wangenyong.dsmvp.view.GankFragmentView;
import com.wangenyong.mvp.base.BaseFragment;
import com.wangenyong.mvp.http.HttpUtils;
import com.wangenyong.mvp.http.ProgressObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GankFragment extends BaseFragment {
    private GankFragmentView contentView = new GankFragmentView();
    private List<Gank> mGanks;

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = contentView.createView(inflater, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGanks = new ArrayList<>();
        contentView.initView(mGanks);
        getGank();
    }

    private void getGank() {
        Observable ob = Api.getDefault().getGank(20, 1);
        HttpUtils.getInstance().toSubscribe(ob, new ProgressObserver<List<Gank>>(getActivity()) {
            @Override
            protected void _onNext(List<Gank> ganks) {
                mGanks.addAll(ganks);
                contentView.notifyDataSetChanged();
            }

            @Override
            protected void _onError(String message) {
                Log.d("Gank", message);
            }
        }, "cacheKey", this, false, false);
    }
}
