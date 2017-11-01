package com.wangenyong.dsmvp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.MyApplication;
import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.databinding.FragmentGankBinding;
import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.dsmvp.http.Repository;
import com.wangenyong.dsmvp.itembinder.GankViewBinder;
import com.wangenyong.mvp.base.BaseFragment;
import com.wangenyong.mvp.http.AbstractProgressObserver;
import com.wangenyong.mvp.utils.UiUtil;
import com.wangenyong.mvp.view.recyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author wangenyong
 */
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
        getRepository().getGankWithCache(new AbstractProgressObserver<List<Gank>>(getContext(), isRefresh, isLoadMore, "") {
            @Override
            protected void onSuccess(List<Gank> ganks) {
                mGanks.addAll(ganks);
                mAdapter.notifyDataSetChanged();
                mBinding.recyclerViewGank.setPullLoadMoreCompleted();
            }

            @Override
            protected void onFail(int code, String message) {
                UiUtil.makeText(getContext(), message);
                mBinding.recyclerViewGank.setPullLoadMoreCompleted();
            }
        }, page, this, isRefresh);

    }

    private Repository getRepository() {
        return ((MyApplication)getActivity().getApplication()).getRepository();
    }

}
