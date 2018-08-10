package com.frame.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * ===================================
 * describe:base类
 * author:zhuang
 * ===================================
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setView(), container, false);
        init(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        initListener();
    }

    //初始化数据
    protected abstract void initData(Bundle savedInstanceState);

    // 添加监听器
    protected abstract void initListener();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    //布局文件
    protected abstract int setView();

    //控件ID
    protected abstract void init(View view);


}
