package com.wangenyong.dsmvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment implements DemoFragmentView.ActionImpl {
    private DemoFragmentView contentView = new DemoFragmentView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = contentView.createView(inflater, savedInstanceState);
        return view;
    }

    @Override
    public void doAction() {

    }

}
