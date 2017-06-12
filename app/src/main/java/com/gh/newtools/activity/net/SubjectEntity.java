package com.gh.newtools.activity.net;

import com.gh.rxretrofitlibrary.api.BaseEntity;

import java.util.List;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class SubjectEntity extends BaseEntity {

    private List<SubjectResulte> data;

    public List<SubjectResulte> getData() {
        return data;
    }

    public void setData(List<SubjectResulte> data) {
        this.data = data;
    }

}
