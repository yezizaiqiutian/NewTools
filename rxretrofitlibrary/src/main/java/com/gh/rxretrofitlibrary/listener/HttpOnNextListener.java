package com.gh.rxretrofitlibrary.listener;

import io.reactivex.Observable;

/**
 * author: gh
 * time: 2017/6/8.
 * description:网络请求的回调处理
 */

public abstract class HttpOnNextListener<T> {

    //无网络情况下在无网缓存时间内走缓存(网络请求失败)
    public static final int NONET_TOCACHE = 1;
    //有网络情况下在有网缓存时间内走缓存(未进行网络请求)
    public static final int HAVENET_TOCACHE = 2;

    /**
     * 成功后回调方法
     * @param t
     */
    public abstract void onNext(T t);

    /**
     * 缓存返回方法
     * @param string
     * @param cacheType
     */
    public void onCacheNext(String string,int cacheType){

    }

    /**
     * 成功后的ober返回，扩展链接式调用
     * @param observable
     */
    public void onNext(Observable observable){

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e
     */
    public  void onError(Throwable e){

    }

    /**
     * 取消回调
     */
    public void onCancel(){

    }

}