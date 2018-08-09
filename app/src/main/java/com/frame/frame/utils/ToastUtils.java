package com.frame.frame.utils;

import android.content.Context;
import android.widget.Toast;

/*
 * ===================================
 * describe:toast工具类
 * author:zhuang
 * ===================================
 */


public class ToastUtils {
    private static Toast toast;
    /**
     * @param context:上下文
     * @param msg：要显示的文字
     */
    public static void showToast(Context context, String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
