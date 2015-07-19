package com.niuti.fuyuan.headwords.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.niuti.fuyuan.headwords.BaseActivity;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.application.MyApplication;
import com.niuti.fuyuan.headwords.fragment.SignFragmentController;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * Created by fuyuan on 2015/7/2.
 */
public class LoginActivity extends FragmentActivity implements
        OnCheckedChangeListener {
    protected String TAG;
    private RadioGroup rg_sign_tab;
    private SignFragmentController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Config.cacheToken(this, "111");
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        controller = SignFragmentController.getInstance(this, R.id.fl_sign);
        controller.showFragment(0);

        initView();
    }

    private void initView() {
        rg_sign_tab = (RadioGroup) findViewById(R.id.rg_sign_tab);
        rg_sign_tab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_sign_in:
                controller.showFragment(0);
                break;
            case R.id.rb_sign_up:
                controller.showFragment(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //MyApplication.getInstance().exit();
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
            //.setIcon(android.R.drawable.ic_dialog_info)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 点击“确认”后的操作
                    LoginActivity.super.onBackPressed();
                    MyApplication.getInstance().exit();
                }
            })
            .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 点击“返回”后的操作,这里不设置没有任何操作
                }
            }).show();
    }
}
