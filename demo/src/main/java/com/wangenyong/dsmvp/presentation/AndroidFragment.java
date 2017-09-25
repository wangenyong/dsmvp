package com.wangenyong.dsmvp.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.view.AndroidFragmentView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends Fragment {
    AndroidFragmentView contentView = new AndroidFragmentView();

    public static AndroidFragment newInstance() {
        return new AndroidFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = contentView.createView(inflater, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contentView.initView();
    }

}
