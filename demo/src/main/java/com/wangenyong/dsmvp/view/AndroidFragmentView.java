package com.wangenyong.dsmvp.view;

import android.widget.TextView;

import com.wangenyong.dsmvp.R;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;

import butterknife.BindView;

/**
 * Created by wangenyong on 2017/9/25.
 */

@BindLayout(R.layout.fragment_android)
public class AndroidFragmentView extends ContentView {
    @BindView(R.id.textView_android)
    TextView mTextView;

    public void initView() {
        mTextView.setText("Hello Android");
    }

}
