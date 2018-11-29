package com.frame.frame.utils;

import android.content.Context;
import android.text.InputType;
import android.widget.TextView;

import com.frame.frame.R;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.MDEditDialog;
import com.wevey.selector.dialog.NormalAlertDialog;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.util.ArrayList;

/**
 * ===================================
 * describe:
 * date:2018/10/8
 * author:zhuang
 * ===================================
 */
public class SelectDialogUtils {
    //单选

    public static void selectType(Context context, final ArrayList list,
                                  DialogInterface.OnItemClickListener<NormalSelectionDialog> dialog) {
        new NormalSelectionDialog.Builder(context).setlTitleVisible(true)   //设置是否显示标题
                .setTitleHeight(50)   //设置标题高度
                .setTitleTextSize(16) //设置标题字体大小 sp
                .setTitleTextColor(R.color.colorPrimary) //设置标题文本颜色
                .setItemHeight(40)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.colorPrimaryDark)  //设置item字体颜色
                .setItemTextSize(14)  //设置item字体大小
                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
                .setOnItemListener(dialog)
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build()
                .setDatas(list)
                .show();
    }

    //提示对话框
    public static void selectHint(Context context, String s, DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog> dialog) {
        new NormalAlertDialog.Builder(context).setTitleVisible(false)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.hei)
                .setContentText(s)
                .setContentTextColor(R.color.hei)
                .setLeftButtonText("取消")
                .setLeftButtonTextColor(R.color.lanse)
                .setRightButtonText("确认")
                .setRightButtonTextColor(R.color.lanse)
                .setOnclickListener(dialog)
                .build()
                .show();
    }

    //编辑对话框
    public static void editingDialog(Context context, String title, String hinttext, final TextView textView,
                                     DialogInterface.OnLeftAndRightClickListener<MDEditDialog> dialog) {
        new MDEditDialog.Builder(context).setTitleVisible(true)
                .setTitleText(title)
                .setTitleTextSize(18)
                .setTitleTextColor(R.color.black_light)
                .setContentText(textView.getText().toString())
                .setContentTextSize(16)
                .setHintText(hinttext)
                .setMaxLines(10)
                .setContentTextColor(R.color.hei)
                .setButtonTextSize(14)
                .setLeftButtonTextColor(R.color.white)
                .setLeftButtonText("取消")
                .setRightButtonTextColor(R.color.white)
                .setRightButtonText("确定")
                .setLineColor(R.color.titlecolor)
                .setInputTpye(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
                .setCanceledOnTouchOutside(false)
                .setOnclickListener(dialog)
                .setMinHeight(0.3f)
                .setWidth(0.8f)
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

}