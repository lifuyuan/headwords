package com.niuti.fuyuan.headwords.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.activity.LoginActivity;
import com.niuti.fuyuan.headwords.activity.MainActivity;
import com.niuti.fuyuan.headwords.application.MyApplication;
import com.niuti.fuyuan.headwords.library.MyWebViewClient;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

public class UserFragment extends BaseFragment {
	private WebView webView;
	protected String TAG;
	private View view;
	private FragmentController controller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_user, null);
		new TitleBuilder(view).setTitleText("初中英语")
			.setRightImage(R.mipmap.dot)
			.setRightOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					controller = FragmentController.getInstance(activity, R.id.fl_content);
					controller.showFragment(2);
				}
			});
		//view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
		//	@Override
		//	public void onClick(View v) {
		//		Config.cacheToken(activity, "");
		//		ToastUtils.showToast(activity, "注销成功", Toast.LENGTH_SHORT);
		//		MyApplication.getInstance().exit();
		//	}
		//});
		webView = (WebView)view.findViewById(R.id.webView);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Logger.show(TAG, "Fragment onStart");
		webView.loadUrl(Config.SET_URL+"?token="+Config.getCachedToken(activity));
		final ProgressDialog prDialog = ProgressDialog.show(activity, null, "loading, please wait...");
		webView.setWebViewClient(new MyWebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				Logger.show(TAG, "-MyWebViewClient->onPageFinished()--");
				prDialog.dismiss();
				super.onPageFinished(view, url);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Logger.show(TAG, "-MyWebViewClient->shouldOverrideUrlLoading()--");
				Logger.show(TAG, "-MyWebViewClient->shouldOverrideUrlLoading()--" + url);
                if(url.endsWith("/android/quit")) {
					Config.cacheToken(activity, "");
					ToastUtils.showToast(activity, "注销成功", Toast.LENGTH_SHORT);
					MyApplication.getInstance().exit();
				}
				//view.loadUrl(url);
				return true;
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
