package com.wangenyong.dsmvp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.databinding.FragmentAndroidBinding;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class AndroidFragment extends Fragment {
    private FragmentAndroidBinding mBinding;

    public static AndroidFragment newInstance() {
        return new AndroidFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_android, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
