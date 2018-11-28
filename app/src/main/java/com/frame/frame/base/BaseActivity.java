package com.frame.frame.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.frame.frame.http.RetrofitUtil;
import com.frame.frame.utils.DownloadUtils;
import com.frame.frame.utils.user_centre.UserCentre;


/**
 * ===================================
 * describe:base类
 * author:zhuang
 * ===================================
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 用来保存所有已打开的Activity
     */
    private InputMethodManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 初始化ui
        initView();
        // 初始化数据
        initData();
        // 添加监听器
        initListener();
    }

    // 初始化ui
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();

    // 添加监听器
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserCentre.getInstance().destroy();
        //RetrofitUtil.getInstance().destroy(); TODO
        DownloadUtils.getDownloadUtils().destroy();
        RetrofitUtil.getInstance().destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}