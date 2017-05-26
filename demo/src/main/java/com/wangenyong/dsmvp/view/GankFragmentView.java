package com.wangenyong.dsmvp.view;

import android.content.Context;

import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.dsmvp.itembinder.GankViewBinder;
import com.wangenyong.mvp.bind.BindId;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.util.UiUtils;
import com.wangenyong.mvp.view.ContentView;
import com.wangenyong.mvp.view.recyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by wangenyong on 2017/5/25.
 */

@BindLayout(R.layout.fragment_gank)
public class GankFragmentView extends ContentView {
    @BindId(R.id.recyclerView_gank)
    PullLoadMoreRecyclerView mRecyclerView;

    private ActionImpl mAction;
    private MultiTypeAdapter mAdapter;


    public void initView(List<Gank> ganks) {
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Gank.class, new GankViewBinder());
        mAdapter.setItems(ganks);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(mAction);
    }

    public void setActionImpl(ActionImpl actionImpl) {
        this.mAction = actionImpl;
    }

    public void showGans() {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.notifyDataSetChanged();
    }

    public void loadMoreSuccess() {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.notifyDataSetChanged();
    }

    public void onError(Context context, String message) {
        mRecyclerView.setPullLoadMoreCompleted();
        UiUtils.makeText(context, message);
    }


    public interface ActionImpl extends PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        void onRefresh();
        @Override
        void onLoadMore();
    }
}
