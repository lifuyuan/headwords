package com.niuti.fuyuan.headwords.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.library.MyWebViewClient;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;


public class MessageFragment extends BaseFragment {
	private WebView webView;
	private View view;
	protected String TAG;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_message, null);
		view.setBackgroundColor(Color.parseColor("#404040"));
		new TitleBuilder(view).setTitleText("首字母");
		webView = (WebView)view.findViewById(R.id.webView);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Logger.show(TAG, "Fragment onStart");
		webView.loadUrl(Config.USER_URL+"?token="+Config.getCachedToken(activity));
		final ProgressDialog prDialog = ProgressDialog.show(activity, null, "loading, please wait...");
		webView.setWebViewClient(new MyWebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				Logger.show(TAG, "-MyWebViewClient->onPageFinished()--");
				prDialog.dismiss();
				super.onPageFinished(view, url);
			}
		});
		webView.setBackgroundColor(Color.parseColor("#404040"));
		webView.setOnLongClickListener(new View.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setSupportZoom(false);
		webSettings.setBuiltInZoomControls(false);
	}

}
