package com.frame.frame.utils;

import android.content.Context;

import com.frame.frame.R;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.NormalAlertDialog;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.util.ArrayList;

/**
 * ===================================
 * describe:对话框
 * author:zhuang
 * ===================================
 */
public class NormalDialogUtils {

    /**
     * <p>
     * 双键对话框
     *
     * @param context
     */
    public static void doubleBondDialog(Context context, String title, String changeLog, String leftbt, String rightbt,
                                        DialogInterface.OnLeftAndRightClickListener dialog) {
        new NormalAlertDialog.Builder(context).setTitleVisible(false)
                .setTitleText(title)
                .setTitleTextColor(R.color.black_light)
                .setContentText(changeLog)
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText(leftbt)
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText(rightbt)
                .setRightButtonTextColor(R.color.black_light)
                .setOnclickListener(dialog)
                .setCanceledOnTouchOutside(false)
                .build()
                .show();
    }

    /**
     * <p>
     * 单键对话框
     *
     * @param context
     */
    public static void singleBondDialog(Context context, String title, String changeLog, String singlebt,
                                        DialogInterface.OnSingleClickListener dialog) {
        new NormalAlertDialog.Builder(context).setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true).setTitleText(title)
                .setTitleTextColor(R.color.colorPrimary)
                .setContentText(changeLog)
                .setContentTextColor(R.color.colorPrimaryDark)
                .setSingleMode(true).setSingleButtonText(singlebt)
                .setSingleButtonTextColor(R.color.colorAccent)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(dialog)
                .setCanceledOnTouchOutside(false)
                .build()
                .show();
    }

    /**
     * <p>
     * 右侧双键对话框
     *
     * @param context
     */
    public static void rightDoubleBondDialog(Context context, String title, String changeLog, String leftbt, String rightbt,
                                             DialogInterface.OnLeftAndRightClickListener dialog) {
        new MDAlertDialog.Builder(context)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText(title)
                .setTitleTextColor(R.color.black_light)
                .setContentText(changeLog)
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText(leftbt)
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText(rightbt)
                .setRightButtonTextColor(R.color.black_light)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setOnclickListener(dialog)
                .setCanceledOnTouchOutside(false)
                .build()
                .show();
    }

    /**
     * <p>
     * 相机相册底部弹出对话框
     *
     * @param context
     */
    public static void cameraRollDialog(Context context,ArrayList<String> strings,
                                             DialogInterface.OnItemClickListener dialog) {
        new NormalSelectionDialog.Builder(context).setlTitleVisible(true)   //设置是否显示标题
                .setTitleHeight(65)   //设置标题高度
                .setTitleText("please select")  //设置标题提示文本
                .setTitleTextSize(14) //设置标题字体大小 sp
                .setTitleTextColor(R.color.colorPrimary) //设置标题文本颜色
                .setItemHeight(40)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.colorPrimaryDark)  //设置item字体颜色
                .setItemTextSize(14)  //设置item字体大小
                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
                .setOnItemListener(dialog)
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build()
                .setDatas(strings)
                .show();
    }
}
