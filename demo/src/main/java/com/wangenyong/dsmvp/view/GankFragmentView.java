package com.wangenyong.dsmvp.view;

import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.entity.Gank;
import com.wangenyong.dsmvp.itembinder.GankViewBinder;
import com.wangenyong.mvp.bind.BindId;
import com.wangenyong.mvp.bind.BindLayout;
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

    private MultiTypeAdapter mAdapter;


    public void initView(List<Gank> ganks) {
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Gank.class, new GankViewBinder());
        mAdapter.setItems(ganks);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

}
