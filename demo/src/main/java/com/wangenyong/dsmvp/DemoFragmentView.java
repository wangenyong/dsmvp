package com.wangenyong.dsmvp;

import com.wangenyong.mvp.bind.BindLayout;
import com.wangenyong.mvp.view.ContentView;

/**
 * Created by wangenyong on 2017/5/25.
 */

@BindLayout(R.layout.fragment_demo)
public class DemoFragmentView extends ContentView {

    private ActionImpl actionImpl;

    public void setActionImpl(ActionImpl actionImpl) {
        this.actionImpl = actionImpl;
    }


    public interface ActionImpl {
        void doAction();
    }
}
