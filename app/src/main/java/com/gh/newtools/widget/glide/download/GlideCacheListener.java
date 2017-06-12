package com.gh.newtools.widget.glide.download;

/**
 * author: gh
 * time: 2017/6/8.
 * description:缓存图片监听
 */

public interface GlideCacheListener {

    void success(String path);

    void error(Exception e);

}
