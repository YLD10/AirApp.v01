package com.xaut.yld10.app;

import android.app.Application;
import android.content.Context;

/**
 * ContextUtil.java
 * @Author : YLD10
 * @EditTime : 2017/5/20 21:24
 **/
public class ContextUtil extends Application {

    private static Context instance;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
