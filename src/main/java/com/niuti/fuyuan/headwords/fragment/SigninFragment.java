package com.niuti.fuyuan.headwords.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.SignBaseFragment;
import com.niuti.fuyuan.headwords.activity.MainActivity;
import com.niuti.fuyuan.headwords.net.Login;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

public class SigninFragment extends SignBaseFragment {
	protected String TAG;
	private View view;
	private EditText username = null;
	private EditText password = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_signin, null);
		view.setBackgroundColor(Color.parseColor("#404040"));
		new TitleBuilder(view).setTitleText("首字母");
        username = (EditText)view.findViewById(R.id.username);
		password = (EditText)view.findViewById(R.id.password);
		view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (TextUtils.isEmpty(username.getText())) {
					ToastUtils.showToast(activity, "昵称不能为空", Toast.LENGTH_SHORT);
					return;
				}

				if (TextUtils.isEmpty(password.getText())) {
					ToastUtils.showToast(activity, "密码不能为空", Toast.LENGTH_SHORT);
					return;
				}


				final ProgressDialog pd = ProgressDialog.show(view.getContext(), "Connecting", "Connecting to server,please wait");
				new Login(username.getText().toString(), password.getText().toString(), new Login.SuccessCallback() {

					@Override
					public void onSuccess(String token) {

						pd.dismiss();

						Config.cacheToken(activity, token);

						//Intent i = new Intent(AtyLogin.this, AtyTimeline.class);
						//i.putExtra(Config.KEY_TOKEN, token);
						//i.putExtra(Config.KEY_PHONE_NUM, etPhone.getText().toString());
						//startActivity(i);
						//finish();
						intent2Activity(MainActivity.class);
						activity.finish();
					}
				}, new Login.FailCallback() {

					@Override
					public void onFail() {
						pd.dismiss();
						ToastUtils.showToast(activity, "登录失败", Toast.LENGTH_SHORT);
					}
				});

			}
		});
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
