package com.frame.frame.qrcode;

import android.app.Activity;
import android.os.Bundle;

import io.github.xudaojie.qrcodelib.CaptureActivity;


/**
 *二维码扫描类
 */

public class SimpleCaptureActivity extends CaptureActivity {
    protected Activity mActivity = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handleResult(String resultString) {
        if (resultString.length() == 17) {
    /*        Intent intent = new Intent(SimpleCaptureActivity.this, ResultActivity.class);
            intent.putExtra("result", resultString);
            startActivity(intent);
            finish();*/
        } else {
            //ToastUtils.showToast(SimpleCaptureActivity.this, "无法识别，请重试");
            restartPreview();
        }
    }

}

