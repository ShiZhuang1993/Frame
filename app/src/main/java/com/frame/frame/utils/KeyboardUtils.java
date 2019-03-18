package com.frame.frame.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * ===================================
 * describe:
 * date:2018/12/12
 * author:zhuang
 * ===================================
 */
public class KeyboardUtils {
    /**
     * 隐藏软键盘
     *
     * @param context :上下文环境，一般为Activity实例
     * @param view    :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     *
     * @param context :上下文环境，一般为Activity实例
     * @param view    :一般为EditText
     */
    public static void showKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        manager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

}
