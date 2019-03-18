package com.frame.frame.utils;

import java.text.DecimalFormat;

/**
 * ===================================
 * describe:各十百千万的转换
 * date:2018/10/16
 * author:zhuang
 * ===================================
 */
public class TransitionUtils {
    private static String s;
    private static float TENTHOUSAND = 10000f;
    private static float ONEHUNDREDMILLION = 100000000f;
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static String intTransition(float num) {
        if (num < TENTHOUSAND) {
            s = decimalFormat.format(num) + "";
        } else if (num >= TENTHOUSAND && num < ONEHUNDREDMILLION) {
            s = decimalFormat.format(num / TENTHOUSAND) + "万";
        } else {
            s = decimalFormat.format(num / ONEHUNDREDMILLION) + "亿";
        }
        return s;
    }
}
