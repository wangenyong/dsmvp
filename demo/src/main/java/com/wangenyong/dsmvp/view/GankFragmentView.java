package com.wangenyong.dsmvp.view;

import com.wangenyong.dsmvp.R;
import com.wangenyong.mvp.bind.BindId;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;
import com.wangenyong.mvp.view.recyclerview.PullLoadMoreRecyclerView;

/**
 * Created by wangenyong on 2017/5/25.
 */

@BindLayout(R.layout.fragment_gank)
public class GankFragmentView extends ContentView {
    @BindId(R.id.recyclerView_gank)
    PullLoadMoreRecyclerView mRecyclerView;



}
