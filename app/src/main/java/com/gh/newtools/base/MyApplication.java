package com.gh.newtools.base;

import android.app.Application;
import android.content.Context;

import com.gh.newtools.BuildConfig;
import com.gh.newtools.R;
import com.gh.newtools.activity.typeface.CustomViewWithTypefaceSupport;
import com.gh.newtools.activity.typeface.TextField;
import com.gh.rxretrofitlibrary.RxRetrofitApp;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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


        //字体    TypefaceActivity
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-ThinItalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
                .addCustomStyle(TextField.class, R.attr.textFieldStyle)
                .build()
        );
    }

}
