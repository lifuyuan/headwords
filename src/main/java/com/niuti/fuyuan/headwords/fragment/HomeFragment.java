package com.niuti.fuyuan.headwords.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.library.MyWebViewClient;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;

public class HomeFragment extends BaseFragment {
	protected String TAG;
	private View view;
	private WebView webView;
	private FragmentController controller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_home, null);
		view.setBackgroundColor(Color.parseColor("#404040"));
		new TitleBuilder(view)
			.setTitleText("初中英语")
			.setRightImage(R.mipmap.dot)
			.setRightOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					controller = FragmentController.getInstance(activity, R.id.fl_content);
					controller.showFragment(2);
				}
			});
		webView = (WebView)view.findViewById(R.id.webView);
		webView.loadUrl(Config.HOME_URL+"?token="+Config.getCachedToken(activity));
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
		return view;
	}

	@Override
	public void onPause() {
		super.onPause();
		Logger.show(TAG, "Fragment onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Logger.show(TAG, "Fragment onStop");
	}

	@Override
	public void onResume() {
		super.onResume();
		Logger.show(TAG, "Fragment onResume");
	}

	@Override
	public void onStart() {
		super.onStart();
		Logger.show(TAG, "Fragment onStart");
	}

	
}
