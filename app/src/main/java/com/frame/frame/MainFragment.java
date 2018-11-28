package com.frame.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frame.frame.base.BaseFragment;

/**
 * ===================================
 * describe:
 * date:2018/11/28
 * author:zhuang
 * ===================================
 */
public class MainFragment extends BaseFragment {

    private TextView tv;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.activity_main, null);
        return view;
    }
}
