package com.gh.rxretrofitlibrary.api;

/**
 * author: gh
 * time: 2017/6/8.
 * description:回调信息统一封装类,返回数据的基类(若采用README中的模式1,2,则采用BaseResultEntity,若采用模式3,则采用BaseEntity)
 */

public class BaseResultEntity<T> {

    //判断标识
    private int ret;
    //提示信息
    private String msg;
    //显示数据（用户需要关心的数据）
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

}
