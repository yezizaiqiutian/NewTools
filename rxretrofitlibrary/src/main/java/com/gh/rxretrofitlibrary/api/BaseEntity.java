package com.gh.rxretrofitlibrary.api;

/**
 * author: gh
 * time: 2017/6/8.
 * description:回调信息统一封装类,返回数据的基类(若采用README中的模式1,2,则采用BaseResultEntity,若采用模式3,则采用BaseEntity)
 */

public class BaseEntity {

    //判断标识
    private int ret;
    //提示信息
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
