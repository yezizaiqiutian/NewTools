package com.gh.newtools.activity.net;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class SubjectResulte {
    private int id;
    private String name;
    private String title;

    @Override
    public String toString() {
        return "name->"+name+"\n";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
