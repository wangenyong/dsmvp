package com.wangenyong.mvp.bind;

/**
 * Created by wangenyong on 2017/5/22.
 */

import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 在ContentView内查找绑定控件
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface BindId {
    @IdRes int value();
}
