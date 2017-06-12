package com.gh.newtools.base;

import android.app.Application;
import android.content.Context;

import com.gh.newtools.BuildConfig;
import com.gh.rxretrofitlibrary.RxRetrofitApp;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class MyApplication extends Application {

    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=getApplicationContext();
        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }

}
