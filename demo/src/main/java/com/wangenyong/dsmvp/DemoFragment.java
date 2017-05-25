package com.wangenyong.dsmvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment implements DemoFragmentView.ActionImpl {
    private DemoFragmentView contentView = new DemoFragmentView();

    private String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
        text = getArguments().getString("text");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = contentView.createView(inflater, savedInstanceState);
        contentView.setActionImpl(this);
        contentView.setTextView(text);
        return view;
    }

    @Override
    public void doAction() {

    }

}
