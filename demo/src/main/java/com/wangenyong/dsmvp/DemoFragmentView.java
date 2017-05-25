package com.wangenyong.dsmvp;

import android.widget.TextView;

import com.wangenyong.mvp.bind.BindId;
import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;

/**
 * Created by wangenyong on 2017/5/25.
 */

@BindLayout(R.layout.fragment_demo)
public class DemoFragmentView extends ContentView {
    @BindId(R.id.textView_demoFragment)
    TextView mTextView;

    private ActionImpl actionImpl;

    public void setActionImpl(ActionImpl actionImpl) {
        this.actionImpl = actionImpl;
    }

    public void setTextView(String text) {
        mTextView.setText(text);
    }

    public interface ActionImpl {
        void doAction();
    }
}
