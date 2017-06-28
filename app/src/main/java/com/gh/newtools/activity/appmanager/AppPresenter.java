package com.gh.newtools.activity.appmanager;

import android.os.Handler;

import com.gh.newtools.base.BaseActivity;
import com.gh.newtools.base.BaseBiz;

import java.util.List;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:
 * description:
 */

public class AppPresenter implements AppContract.Presenter{

    private AppContract.View mView;
    private BaseActivity mActivity;
    private Handler mHandler;
    private BaseBiz mBiz;

    public AppPresenter(AppContract.View view) {

        mView = view;
        mActivity = (BaseActivity) view;
        this.mHandler = new Handler();
        this.mBiz = new AppBiz();
        mView.setPresenter(this);

    }

    @Override
    public void getData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mBiz.getData(mActivity, new BaseBiz.OnLoadListener() {
                    @Override
                    public void loading() {
                        mView.showLoading();
                    }

                    @Override
                    public void loadSuccess(final List<AppBean> list) {
                        mView.hideLoading();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mView.referData(list);
                            }
                        });
                    }

                    @Override
                    public void loadFaile() {
                        mView.hideLoading();
                    }
                });
            }
        }).start();

    }

}
