package com.gh.newtools.base;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:
 * description:
 */

public interface BaseView<T> {

    /**
     * 在Presenter的构造方法中进行调用
     * 在继承View的Activity/Fragment中进行初始化操作
     * @param presenter
     */
    void setPresenter(T presenter);

}
