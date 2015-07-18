package com.niuti.fuyuan.headwords.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.SignBaseFragment;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;

public class SigninFragment extends SignBaseFragment {
	protected String TAG;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_signin, null);
		view.setBackgroundColor(Color.parseColor("#2E2E2E"));
		new TitleBuilder(view).setTitleText("首字母");

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
