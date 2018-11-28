package com.frame.frame.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.frame.frame.R;

/**
 * 自定义dialog自定义底部弹出
 */

public abstract class BottomShareMenu extends Dialog{

    public BottomShareMenu(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        setContentView(onBindView());
        Window window=this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.ButtomMenuEnterExitAnimation);
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        lp.height = (int)(display.getHeight()/1.8); //设置高度
        window.setAttributes(lp);
        setData();
    }
    protected abstract View onBindView();
    protected abstract void setData();
}