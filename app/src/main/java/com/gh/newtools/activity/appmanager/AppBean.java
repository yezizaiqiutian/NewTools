package com.gh.newtools.activity.appmanager;

import android.graphics.drawable.Drawable;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:https://github.com/h4de5ing/AndroidCommon
 * description:App信息Bean
 */

public class AppBean {
    private String appName;
    private Drawable appIcon;
    private long appDate;
    private long appSize;
    private String appAPk;
    private String appVerName;
    private int appVerCode;
    private String appInstaller;
    private boolean isSystemApp;
    private String appPackage;

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public long getAppDate() {
        return appDate;
    }

    public void setAppDate(long appDate) {
        this.appDate = appDate;
    }

    public long getAppSize() {
        return appSize;
    }

    public void setAppSize(long appSize) {
        this.appSize = appSize;
    }

    public String getAppAPk() {
        return appAPk;
    }

    public void setAppAPk(String appAPk) {
        this.appAPk = appAPk;
    }

    public String getAppVerName() {
        return appVerName;
    }

    public void setAppVerName(String appVerName) {
        this.appVerName = appVerName;
    }

    public int getAppVerCode() {
        return appVerCode;
    }

    public void setAppVerCode(int appVerCode) {
        this.appVerCode = appVerCode;
    }

    public String getAppInstaller() {
        return appInstaller;
    }

    public void setAppInstaller(String appInstaller) {
        this.appInstaller = appInstaller;
    }

    public boolean isSystemApp() {
        return isSystemApp;
    }

    public void setSystemApp(boolean systemApp) {
        isSystemApp = systemApp;
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "appName='" + appName + '\'' +
                ", appDate=" + appDate +
                ", appSize=" + appSize +
                ", appAPk='" + appAPk + '\'' +
                ", appVerName='" + appVerName + '\'' +
                ", appVerCode=" + appVerCode +
                ", appInstaller='" + appInstaller + '\'' +
                ", isSystemApp=" + isSystemApp +
                '}';
    }
}
