package com.gh.newtools.activity.net.file;

import com.gh.rxretrofitlibrary.api.BaseEntity;

/**
 * 上传回调结果
 * Created by WZG on 2016/10/20.
 */

public class UploadResulte extends BaseEntity{

    /**
     * headImgUrl : http://www.izaodao.com/uc/data/avatar/004/81/14/20_avatar_middle.jpg?timestamp=1476943817
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
