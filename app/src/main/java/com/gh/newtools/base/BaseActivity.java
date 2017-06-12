package com.gh.newtools.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.gh.newtools.R;
import com.gh.newtools.utils.AppManager;
import com.gh.statusbarlibrary.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public abstract class BaseActivity extends RxAppCompatActivity{

    protected Context mContext = null;
    protected Activity mActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;

        //设置没有Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //记住每个Activity,用于退出登录
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    /**
     * 当Activity彻底运行起来之后回调onPostCreate方法
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initBundle();
        initTitle();
        initView();
        initData();
        initListener();
        initLoad();
    }

    /**
     * 设置状态栏颜色,需要在初始化View后调用
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
    }

    /**
     * 获取上个界面传输的intent
     */
    private void initBundle() {
    }

    /**
     * Title
     */
    protected void initTitle() {
    }

    /**
     * 加载布局
     */
    protected void initView() {
    }

    /**
     * 加载数据
     */
    protected void initData() {
    }

    /**
     * 加载监听
     */
    protected void initListener() {
    }

    /**
     * 加载url
     */
    protected void initLoad() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记住每个Activity,用于退出登录
        AppManager.getAppManager().finishActivity(this);
    }

}
