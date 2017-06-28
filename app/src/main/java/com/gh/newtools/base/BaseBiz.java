package com.gh.newtools.base;

import android.content.Context;

import com.gh.newtools.activity.appmanager.AppBean;

import java.util.List;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:https://github.com/h4de5ing/AndroidCommon
 * description:借鉴
 */

public interface BaseBiz {

    void getData(Context context, OnLoadListener onLoadListener);

    interface OnLoadListener {
        void loading();

        void loadSuccess(List<AppBean> list);

        void loadFaile();
    }

}
