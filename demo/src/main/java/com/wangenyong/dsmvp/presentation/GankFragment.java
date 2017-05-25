package com.wangenyong.dsmvp.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.view.GankFragmentView;

public class GankFragment extends Fragment {
    private GankFragmentView contentView = new GankFragmentView();

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

    }

}
