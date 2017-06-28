package com.gh.newtools.activity.appmanager;

import com.gh.newtools.base.BasePresenter;
import com.gh.newtools.base.BaseView;

import java.util.List;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:
 * description:
 */

public interface AppContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void referData(List<AppBean> list);
    }

    interface Presenter extends BasePresenter {

        void getData();

    }

}
