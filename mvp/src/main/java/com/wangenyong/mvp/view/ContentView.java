package com.wangenyong.mvp.view;

/**
 * Created by wangenyong on 2017/5/22.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.wangenyong.mvp.bind.BindClick;
import com.wangenyong.mvp.bind.BindId;
import com.wangenyong.mvp.bind.BindLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * View的基类，处理所有有关View的操作
 */
public class ContentView {
    private ClickImpl clickImpl = new ClickImpl(this);

    public View createView(Context context, @Nullable Bundle savedInstanceState) {
        return createView(LayoutInflater.from(context), savedInstanceState);
    }

    public View createView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        int layout = getClass().getAnnotation(BindLayout.class).value();
        View view = inflater.inflate(layout, null);
        clickImpl.setOnClickListener(view);
        setViewField(this, view);
        return view;
    }

    private class ClickImpl implements View.OnClickListener {
        private SparseArray<Method> mds = new SparseArray<>();
        private ContentView cv;

        private ClickImpl(ContentView cv) {
            long t = System.currentTimeMillis();
            this.cv = cv;
            Method[] ms = ContentView.this.getClass().getMethods();
            for (Method m : ms) {
                BindClick clk = m.getAnnotation(BindClick.class);
                if (clk != null) {
                    int id = clk.value();
                    mds.put(id, m);
                }
            }
            System.out.println("点击事件处理消耗-------------》 " + (System.currentTimeMillis() - t));
        }

        private void setOnClickListener(View view) {
            for (int i = 0, l = mds.size(); i < l; i++) {
                int id = mds.keyAt(i);
                View v = view.findViewById(id);
                if (v != null) v.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            Method m = mds.get(v.getId());
            try {
                if (m != null) m.invoke(cv, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static<T extends ContentView> void setViewField(T cv, View view) {
        long t = System.currentTimeMillis();
        Field[] fields = cv.getClass().getDeclaredFields();
        for (Field field : fields) {
            BindId bind = field.getAnnotation(BindId.class);
            if (bind != null) {
                int id = bind.value();
                field.setAccessible(true);
                try {
                    System.out.println("控件处理--------> " + id);
                    field.set(cv, view.findViewById(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("控件处理消耗-------------》 " + (System.currentTimeMillis() - t));
    }
}

