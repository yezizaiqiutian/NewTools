package com.gh.newtools.utils;

import android.text.TextUtils;

/**
 * author: gh
 * time: 2017/6/9.
 * description:字符串判空工具
 */

public class EmptyUtils {

    /**
     * 判断String是否为空
     * 当为null，设置为""
     * @param string
     * @return
     */
    public static String EmptyString(String string) {
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /**
     * 判断String是否为空
     * 当为null，设置为temp
     * @param string
     * @param temp
     * @return
     */
    public static String EmptyString(String string,String temp) {
        return TextUtils.isEmpty(string) ? temp : string;
    }

}
