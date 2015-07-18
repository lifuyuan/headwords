package com.niuti.fuyuan.headwords.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;
import com.niuti.fuyuan.headwords.widget.AutoHorizontalLinearLayout;
import com.niuti.fuyuan.headwords.widget.FixGridLayout;

import java.util.Random;

public class HomeFragment extends BaseFragment {

	private View view;
	private WebView webView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_home, null);
		
		new TitleBuilder(view).setTitleText("首字母");

		webView = (WebView)view.findViewById(R.id.webView);
		webView.loadUrl("http://121.40.201.41/android/learnings/new_learning?token=XmK7EHDvzCy_PTKc8TrG");
		webView.setWebViewClient(new WebViewClient());
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		return view;
	}
	
}
