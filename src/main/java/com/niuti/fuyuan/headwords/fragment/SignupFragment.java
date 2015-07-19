package com.niuti.fuyuan.headwords.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.SignBaseFragment;
import com.niuti.fuyuan.headwords.activity.MainActivity;
import com.niuti.fuyuan.headwords.net.Signup;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

public class SignupFragment extends SignBaseFragment {
	protected String TAG;
	private View view;
	private Spinner spinner;
	private String[] dataSource = new String[]{"选择年级","6年级","7年级","8年级","9年级"};
	private String[] mapdataSource = new String[]{"","6","7","8","9"};
	private int grade_position = 0;
	private EditText username;
	private EditText password;
	private EditText password_confirm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_signup, null);
		view.setBackgroundColor(Color.parseColor("#2E2E2E"));
		new TitleBuilder(view).setTitleText("首字母");
		username = (EditText)view.findViewById(R.id.username);
		password = (EditText)view.findViewById(R.id.password);
		password_confirm = (EditText)view.findViewById(R.id.password_confirm);
		spinner = (Spinner)view.findViewById(R.id.grade);
        spinner.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_gallery_item, dataSource));
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				grade_position = position;
				Logger.show(TAG, dataSource[grade_position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(username.getText())) {
					ToastUtils.showToast(activity, "昵称不能为空", Toast.LENGTH_SHORT);
					return;
				}

				if (TextUtils.isEmpty(password.getText())) {
					ToastUtils.showToast(activity, "密码不能为空", Toast.LENGTH_SHORT);
					return;
				}

				if (password.getText().length() < 8) {
					ToastUtils.showToast(activity, "密码长度不能小于8位", Toast.LENGTH_SHORT);
					return;
				}

				if (TextUtils.isEmpty(password_confirm.getText())) {
					ToastUtils.showToast(activity, "确认密码不能为空", Toast.LENGTH_SHORT);
					return;
				}

				if (!password_confirm.getText().toString().equals(password.getText().toString()) ) {
					ToastUtils.showToast(activity, "确认密码与密码不一致", Toast.LENGTH_SHORT);
					return;
				}

				if (grade_position == 0) {
					ToastUtils.showToast(activity, "请选择年级", Toast.LENGTH_SHORT);
					return;
				}
				final ProgressDialog pd = ProgressDialog.show(view.getContext(), "Connecting", "Connecting to server,please wait");
				new Signup(username.getText().toString(), password.getText().toString(), password_confirm.getText().toString(), mapdataSource[grade_position], new Signup.SuccessCallback() {
					@Override
					public void onSuccess(String token) {
						pd.dismiss();

						Config.cacheToken(activity, token);

						intent2Activity(MainActivity.class);
						activity.finish();
					}
				}, new Signup.FailCallback() {
					@Override
					public void onFail() {
						pd.dismiss();
						ToastUtils.showToast(activity, "注册失败，您的昵称已被使用", Toast.LENGTH_SHORT);
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
