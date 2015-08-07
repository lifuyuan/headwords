package com.niuti.fuyuan.headwords.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.SignBaseFragment;
import com.niuti.fuyuan.headwords.activity.MainActivity;
import com.niuti.fuyuan.headwords.net.Getcode;
import com.niuti.fuyuan.headwords.net.Signup;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

public class SignupFragment extends SignBaseFragment {
	protected String TAG;
	private View view;
	//private Spinner spinner;
	//private String[] dataSource = new String[]{"选择年级","6年级","7年级","8年级","9年级"};
	//private String[] mapdataSource = new String[]{"","6","7","8","9"};
	private String grade_position = null;
	private EditText username;
	private EditText password;
	private EditText password_confirm;
	private EditText etcode;
	private ToggleButton toggle6;
	private ToggleButton toggle7;
	private ToggleButton toggle8;
	private ToggleButton toggle9;
	private Button getcode;
	private TimeCount time;
	private static String ENCODING = "UTF-8";
	private String code ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_signup, null);
		view.setBackgroundColor(Color.parseColor("#FFFFFF"));
		new TitleBuilder(view).setTitleText("手机号注册");
		username = (EditText)view.findViewById(R.id.username);
		password = (EditText)view.findViewById(R.id.password);
		password_confirm = (EditText)view.findViewById(R.id.password_confirm);
		etcode = (EditText)view.findViewById(R.id.code);
		toggle6 = (ToggleButton)view.findViewById(R.id.toggle6);
		toggle6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1)
			{
				if(arg1)
				{
					toggle7.setChecked(false);
					toggle8.setChecked(false);
					toggle9.setChecked(false);
					grade_position = "6";
				}
				else
				{
					grade_position = null;
				}
			}
		});
		toggle7 = (ToggleButton)view.findViewById(R.id.toggle7);
		toggle7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1)
			{
				if(arg1)
				{
					toggle6.setChecked(false);
					toggle8.setChecked(false);
					toggle9.setChecked(false);
					grade_position = "7";
				}
				else
				{
					grade_position = null;
				}
			}
		});
		toggle8 = (ToggleButton)view.findViewById(R.id.toggle8);
		toggle8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1)
			{
				if(arg1)
				{
					toggle7.setChecked(false);
					toggle6.setChecked(false);
					toggle9.setChecked(false);
					grade_position = "8";
				}
				else
				{
					grade_position = null;
				}
			}
		});
		toggle9 = (ToggleButton)view.findViewById(R.id.toggle9);
		toggle9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1)
			{
				if(arg1)
				{
					toggle7.setChecked(false);
					toggle8.setChecked(false);
					toggle6.setChecked(false);
					grade_position = "9";
				}
				else
				{
					grade_position = null;
				}
			}
		});
		//spinner = (Spinner)view.findViewById(R.id.grade);
        //spinner.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_gallery_item, dataSource));
		/*
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
        */
		time = new TimeCount(120000, 1000);//构造CountDownTimer对象
		getcode = (Button)view.findViewById(R.id.getCode);
		getcode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(username.getText())) {
					ToastUtils.showToast(activity, "手机号不能为空", Toast.LENGTH_SHORT);
					return;
				}
				final ProgressDialog pd1 = ProgressDialog.show(view.getContext(), "Connecting", "Connecting to server,please wait");
				new Getcode(username.getText().toString(), new Getcode.SuccessCallback() {
					@Override
					public void onSuccess(String code) {
						pd1.dismiss();
						Config.cacheCode(activity, code);
					}
				}, new Getcode.FailCallback() {
					@Override
					public void onFail() {
						pd1.dismiss();
						ToastUtils.showToast(activity, "发送短信失败！", Toast.LENGTH_SHORT);
					}
				});
				time.start();//开始计时
			}
		});
		view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(etcode.getText())) {
					ToastUtils.showToast(activity, "短信验证码不能为空", Toast.LENGTH_SHORT);
					return;
				}

				if (!etcode.getText().toString().equals(Config.getCachedCode(activity))) {
					ToastUtils.showToast(activity, "短信验证码错误", Toast.LENGTH_SHORT);
					return;
				}

				if (TextUtils.isEmpty(username.getText())) {
					ToastUtils.showToast(activity, "手机号不能为空", Toast.LENGTH_SHORT);
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

				if (TextUtils.isEmpty(grade_position)) {
					ToastUtils.showToast(activity, "请选择年级", Toast.LENGTH_SHORT);
					return;
				}
				final ProgressDialog pd = ProgressDialog.show(view.getContext(), "Connecting", "Connecting to server,please wait");
				new Signup(username.getText().toString(), password.getText().toString(), password_confirm.getText().toString(), grade_position, new Signup.SuccessCallback() {
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
						ToastUtils.showToast(activity, "注册失败，您的手机号已经注册过，请您到登录页面进行登录操作", Toast.LENGTH_SHORT);
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

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
		}
		@Override
		public void onFinish() {//计时完毕时触发
			getcode.setText("重新发送验证码");
			getcode.setClickable(true);
			getcode.setEnabled(true);
		}
		@Override
		public void onTick(long millisUntilFinished){//计时过程显示
			getcode.setClickable(false);
			getcode.setEnabled(false);
			getcode.setText("重新发送验证码（"+millisUntilFinished /1000+"秒后）");
		}
	}

}

