package com.wangenyong.dsmvp.presentation;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.databinding.FragmentGankBinding;
import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.dsmvp.http.Api;
import com.wangenyong.dsmvp.itembinder.GankViewBinder;
import com.wangenyong.mvp.base.BaseFragment;
import com.wangenyong.mvp.http.HttpUtils;
import com.wangenyong.mvp.http.ProgressObserver;
import com.wangenyong.mvp.util.UiUtils;
import com.wangenyong.mvp.view.recyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import me.drakeet.multitype.MultiTypeAdapter;

public class GankFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private List<Gank> mGanks = new ArrayList<>();
    private int mPage = 1;

    private FragmentGankBinding mBinding;
    private MultiTypeAdapter mAdapter;

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Gank.class, new GankViewBinder());
        mAdapter.setItems(mGanks);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gank, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        getGank(mPage, false, false);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        getGank(mPage, true, false);
    }

    private void initRecyclerView() {
        mBinding.recyclerViewGank.setLinearLayout();
        mBinding.recyclerViewGank.setAdapter(mAdapter);
        mBinding.recyclerViewGank.setOnPullLoadMoreListener(this);
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
                if (isRefresh) {
                    ganks.clear();
                }
                mGanks.addAll(ganks);
                mBinding.recyclerViewGank.setPullLoadMoreCompleted();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            protected void _onError(String message) {
                Log.d("Gank", message);
                if (isLoadMore) {
                    mPage--;
                }
                mBinding.recyclerViewGank.setPullLoadMoreCompleted();
                UiUtils.makeText(getActivity(), message);
            }
        }, "cacheKey", this, false, true);
    }

}
