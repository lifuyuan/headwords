package com.niuti.fuyuan.headwords.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseFragment;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.activity.LoginActivity;
import com.niuti.fuyuan.headwords.activity.MainActivity;
import com.niuti.fuyuan.headwords.application.MyApplication;
import com.niuti.fuyuan.headwords.utils.TitleBuilder;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

public class UserFragment extends BaseFragment {
	
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_user, null);
		new TitleBuilder(view).setTitleText("首字母");
		view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Config.cacheToken(activity, "");
				ToastUtils.showToast(activity, "注销成功", Toast.LENGTH_SHORT);
				MyApplication.getInstance().exit();
			}
		});
		return view;
	}
}
