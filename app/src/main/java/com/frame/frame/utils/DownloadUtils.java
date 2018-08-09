package com.frame.frame.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

/**
 * ===================================
 * describe:调用手机自带下载功能
 * author:zhuang
 * ===================================
 */
public class DownloadUtils {
    private static DownloadUtils downloadutils;

    //单列模式
    public static DownloadUtils getDownloadUtils() {
        if (downloadutils == null) {
            synchronized (DownloadUtils.class) {
                downloadutils = new DownloadUtils();
            }
        }
        return downloadutils;
    }

    /**
     * 调用手机专用下载
     *
     * @param url
     */
    public void downloadAPK(String url, Context context, String appmane) {
        DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        // 设置下载路径和文件名
        request.setDestinationInExternalPublicDir("app", appmane + ".apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");
        // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        // 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);
        long refernece = dManager.enqueue(request);
        // 把当前下载的ID保存起来
        SharedPreferences sPreferences = context.getSharedPreferences("downloadcomplete", 0);
        sPreferences.edit().putLong("refernece", refernece).commit();
    }

    //调用系统下载
    public void winDownload(String downloadUrl, Context context) {
        DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        String[] urlname = downloadUrl.split("/");
        // 设置下载路径和文件名
        request.setDestinationInExternalPublicDir("app", urlname[urlname.length - 1]);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        // 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);
        long refernece = dManager.enqueue(request);
    }

    public void destroy(){
        downloadutils=null;
    }
}
