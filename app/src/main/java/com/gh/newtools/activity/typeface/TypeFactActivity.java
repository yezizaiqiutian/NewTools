package com.gh.newtools.activity.typeface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * author: gh
 * time: 2017/9/27.
 * Github:https://github.com/chrisjenx/Calligraphy
 * http://www.jianshu.com/p/5d4e6ae8ba4e
 * description:字体设置
 * 步骤
 * 1.导包
 * compile 'uk.co.chrisjenx:calligraphy:2.3.0'
 * 2.Application中配置
 * 3.Activity中添加    可在Base中添加
 *
 * @Override protected void attachBaseContext(Context newBase) {
 * super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
 * }
 * 4.assest中添加字体库
 * 5.导入相关工具类
 * CustomViewWithTypefaceSupport
 * TextField
 * 6.导入attr
 * 7.布局中添加
 * fontPath="fonts/gtw.ttf"
 */

public class TypeFactActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TypeFactActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeface);
        ButterKnife.bind(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
