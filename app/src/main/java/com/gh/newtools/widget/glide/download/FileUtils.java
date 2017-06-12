package com.gh.newtools.widget.glide.download;

import java.io.File;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class FileUtils {

    //判断文件是否存在
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
