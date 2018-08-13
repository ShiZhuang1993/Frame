package com.frame.frame.init;

import android.content.Context;

import com.frame.frame.http.RetrofitUtil;
import com.frame.frame.utils.CrashHandler;
import com.frame.frame.utils.DownloadUtils;
import com.frame.frame.utils.user_centre.UserCentre;

import org.litepal.LitePalApplication;

/**
 * ===================================
 * describe:初始化布局
 * author:zhuang
 * ===================================
 */
    //由于用到数据库的关系把这里的Application改成为LitePalApplication
    // （这里主要是为数据库而改变的，其他的功能与Application百分之百兼容）
    //也可以再onCreate中去配置LitePal.initialize(this);
public class AppInit extends LitePalApplication {
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
        //RetrofitUtil.getInstance(); TODO
        UserCentre.getInstance();
        DownloadUtils.getDownloadUtils();
    }
}
