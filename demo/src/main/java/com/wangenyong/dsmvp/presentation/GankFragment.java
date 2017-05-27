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

public class GankFragment extends BaseFragment implements GankFragmentView.ActionImpl {
    private GankFragmentView contentView = new GankFragmentView();
    private List<Gank> mGanks;
    private int mPage = 1;

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = contentView.createView(inflater, savedInstanceState);
        contentView.setActionImpl(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGanks = new ArrayList<>();
        contentView.initView(mGanks);
        getGank(mPage, false, false);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        getGank(mPage, true, false);
    }

    @Override
    public void onLoadMore() {
        getGank(++mPage, false, true);
    }

    private void getGank(int page, final boolean isRefresh, final boolean isLoadMore) {
        Observable ob = Api.getDefault().getGank(20, page);
        HttpUtils.getInstance().toSubscribe(ob, new ProgressObserver<List<Gank>>(getActivity(), isRefresh, isLoadMore, " ") {
            @Override
            protected void _onNext(List<Gank> ganks) {
                mGanks.addAll(ganks);
                if (isLoadMore) {
                    contentView.loadMoreSuccess();
                } else {
                    ganks.clear();
                    contentView.showGans();
                }
            }

            @Override
            protected void _onError(String message) {
                Log.d("Gank", message);
                if (isLoadMore) {
                    mPage--;
                }
                contentView.onError(getActivity(), message);
            }
        }, "cacheKey", this, false, true);
    }

}
