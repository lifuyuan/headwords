package com.niuti.fuyuan.headwords.fragment;

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
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.library.MyWebViewClient;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;

public class HomeFragment extends BaseFragment {
	protected String TAG;
	private View view;
	private WebView webView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_home, null);
		view.setBackgroundColor(Color.parseColor("#2E2E2E"));
		new TitleBuilder(view).setTitleText("首字母");
		webView = (WebView)view.findViewById(R.id.webView);
		webView.loadUrl("http://121.40.201.41/android/learnings/new_learning?token=XmK7EHDvzCy_PTKc8TrG");
		webView.setWebViewClient(new MyWebViewClient());
		webView.setBackgroundColor(Color.parseColor("#2E2E2E"));
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
