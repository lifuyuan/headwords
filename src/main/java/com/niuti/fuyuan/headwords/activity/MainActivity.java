package com.niuti.fuyuan.headwords.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.application.MyApplication;
import com.niuti.fuyuan.headwords.fragment.FragmentController;
import com.niuti.fuyuan.headwords.utils.Logger;

public class MainActivity extends FragmentActivity implements
        OnCheckedChangeListener {
    protected String TAG;
    private RadioGroup rg_tab;
    private RadioButton rb_home;
    //private ImageView iv_add;
    private FragmentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        controller = FragmentController.getInstance(this, R.id.fl_content);
        controller.showFragment(0);

        initView();
    }

    private void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);

        rg_tab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                controller.showFragment(0);
                rb_home = (RadioButton)group.findViewById(R.id.rb_home);
                rb_home.setChecked(false);
                break;
            case R.id.rb_meassage:
                controller.showFragment(1);
                break;
            case R.id.rb_user:
                controller.showFragment(2);
                break;
            default:
                break;
        }
    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //case R.id.iv_add:
            //    ToastUtils.showToast(this, "add", Toast.LENGTH_SHORT);
            //    break;

            default:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    };
*/
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
                    MainActivity.super.onBackPressed();
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

    @Override
    public void onPause() {
        super.onPause();
        Logger.show(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.show(TAG, "onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.show(TAG, "onResume");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Logger.show(TAG, "onRestart");
    }

}
