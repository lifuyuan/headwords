package com.niuti.fuyuan.headwords.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;


public class SignFragmentController {

	private int containerId;
	private FragmentManager fm;
	private ArrayList<Fragment> fragments;

	private static SignFragmentController controller;

	public static SignFragmentController getInstance(FragmentActivity activity, int containerId) {
		if (controller == null) {
			controller = new SignFragmentController(activity, containerId);
		}
		return controller;
	}

	private SignFragmentController(FragmentActivity activity, int containerId) {
		this.containerId = containerId;
		fm = activity.getSupportFragmentManager();
		initFragment();
	}

	private void initFragment() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new SigninFragment());
		fragments.add(new SignupFragment());
		
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			ft.add(containerId, fragment);
		}
		ft.commit();
	}

	public void showFragment(int position) {
		hideFragments();
		Fragment fragment = fragments.get(position);
		FragmentTransaction ft = fm.beginTransaction();
		ft.show(fragment);
		ft.commit();

	}
	
	public void hideFragments() {
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			if(fragment != null) {
				ft.hide(fragment);
			}
		}
		ft.commit();

	}
	
	public Fragment getFragment(int position) {
		return fragments.get(position);
	}
}