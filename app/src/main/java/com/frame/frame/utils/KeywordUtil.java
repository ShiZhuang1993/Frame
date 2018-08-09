package com.frame.frame.utils;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
/*
 * ===================================
 * describe:部分字体高亮
 * author:zhuang
 * ===================================
 */
public class KeywordUtil {

    /**
     * 关键字高亮变色
     *
     * 实用方法   KeywordUtil.highlight(text,keyword)
     *
     * @param color
     *            变化的色值
     * @param text
     *            文字
     * @param keyword
     *            文字中的关键字
     * @return
     */
    public static SpannableStringBuilder highlight(String text, String target) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(Color.RED);
            spannable.setSpan(span, m.start(), m.end(),
                    SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

}