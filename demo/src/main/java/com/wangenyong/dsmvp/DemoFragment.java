package com.wangenyong.dsmvp;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.databinding.FragmentDemoBinding;
import com.wangenyong.mvp.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends BaseFragment {
    private FragmentDemoBinding mBinding;
    private String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
        text = getArguments().getString("text");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_demo, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.textViewDemoFragment.setText(text);
    }
}
