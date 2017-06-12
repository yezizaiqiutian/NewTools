package com.gh.rxretrofitlibrary.exception;

/**
 * author: gh
 * time: 2017/6/8.
 * description:自定义错误信息，统一处理返回处理
 */

public class HttpTimeException extends RuntimeException {

    public static final int NO_DATA = 0x2;

    public HttpTimeException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public HttpTimeException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 转换错误数据
     *
     * @param code
     * @return
     */
    // TODO: 2017/5/26 根据服务器返回的错误码进行相应转换
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case NO_DATA:
                message = "无数据";
                break;
            default:
                message = "error";
                break;

        }
        return message;
    }

}