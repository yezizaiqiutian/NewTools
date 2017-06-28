package com.gh.newtools.activity.appmanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.gh.newtools.base.BaseBiz;
import com.gh.newtools.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:https://github.com/h4de5ing/AndroidCommon
 * description:
 */

public class AppBiz implements BaseBiz{

    @Override
    public void getData(Context c, OnLoadListener onLoadListener) {
        List<AppBean> list = new ArrayList<>();
        List<PackageInfo> installedPackages = c.getPackageManager().getInstalledPackages(0);
        for (PackageInfo info : installedPackages) {
            String appName = AppUtils.getAppName(c, info.packageName);
            Drawable appIcon = AppUtils.getAppIcon(c, info.packageName);
            long appDate = 100;
//            long appDate = AppUtils.getAppDate(c, info.packageName);
            long appSize = AppUtils.getAppSize(c, info.packageName);
            String appApk = AppUtils.getAppApk(c, info.packageName);
            String appVersionName = AppUtils.getAppVersionName(c, info.packageName);
            int appVersionCode = AppUtils.getAppVersionCode(c, info.packageName);
            String appInstaller = AppUtils.getAppInstaller(c, info.packageName);
            boolean systemApp = AppUtils.isSystemApp(c, info.packageName);
            if (!TextUtils.isEmpty(appName) &&
                    !TextUtils.isEmpty(appApk) &&
                    !TextUtils.isEmpty(appVersionName) &&
                    !TextUtils.isEmpty(appInstaller) &&
                    appIcon != null
                    && appDate > 0
                    && appSize > 0
                    && appVersionCode > 0) {
                AppBean appBean = new AppBean();
                appBean.setAppName(appName);
                appBean.setAppIcon(appIcon);
                appBean.setAppDate(appDate);
                appBean.setAppSize(appSize);
                appBean.setAppAPk(appApk);
                appBean.setAppVerName(appVersionName);
                appBean.setAppVerCode(appVersionCode);
                appBean.setAppInstaller(appInstaller);
                appBean.setSystemApp(systemApp);
                appBean.setAppPackage(info.packageName);
                list.add(appBean);
            }
        }
        onLoadListener.loadSuccess(list);
    }

}
