package com.niuti.fuyuan.headwords.library;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

/**
 * Created by fuyuan on 2015/7/19.
 */
public class MyWebViewClient extends WebViewClient{
    protected String TAG;
    private ProgressDialog prDialog;
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Logger.show(TAG, "-MyWebViewClient->shouldOverrideUrlLoading()--");
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Logger.show(TAG, "-MyWebViewClient->onPageStarted()--");
        prDialog = ProgressDialog.show(view.getContext(), null, "loading, please wait...");
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Logger.show(TAG, "-MyWebViewClient->onPageFinished()--");
        prDialog.dismiss();
        super.onPageFinished(view, url);
    }


    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        Logger.show(TAG, "MyWebViewClient.onReceivedError.errorCode=" + String.valueOf(errorCode));
        Logger.show(TAG, "MyWebViewClient.onReceivedError.description=" + description);
        Logger.show(TAG, "MyWebViewClient.onReceivedError.failingUrl=" + failingUrl);
        if(errorCode==-2) {
            view.loadUrl("file:///android_asset/weberror.html");
            ToastUtils.showToast(view.getContext(), "网络错误", Toast.LENGTH_SHORT);
        } else {
            view.loadUrl("file:///android_asset/errorpage.html");
        }
        super.onReceivedError(view, errorCode, description, failingUrl);

        // Log.i(TAG, "-MyWebViewClient->onReceivedError()--\n errorCode="+errorCode+" \ndescription="+description+" \nfailingUrl="+failingUrl);
    }
}
