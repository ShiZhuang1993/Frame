package com.frame.frame.init;

import android.app.Application;
import android.content.Context;

import com.frame.frame.http.RetrofitUtil;
import com.frame.frame.utils.CrashHandler;
import com.frame.frame.utils.DownloadUtils;
import com.frame.frame.utils.user_centre.UserCentre;

/**
 * ===================================
 * describe:初始化布局
 * author:zhuang
 * ===================================
 */
public class AppInit extends Application {
    private static Context mContext;

    //全局上下文
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initSingleMode();
        //全局捕获异常初始化
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    //对所有的单列模式做初始化
    private void initSingleMode() {
        RetrofitUtil.getInstance();
        UserCentre.getInstance();
        DownloadUtils.getDownloadUtils();
    }
}
