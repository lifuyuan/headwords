package com.niuti.fuyuan.headwords.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.niuti.fuyuan.headwords.BaseActivity;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.fragment.FragmentController;

/**
 * Created by fuyuan on 2015/7/2.
 */
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config.cacheToken(this, "111");
        setContentView(R.layout.activity_login);
    }
}
