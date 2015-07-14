package com.niuti.fuyuan.headwords.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.fragment.FragmentController;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

public class MainActivity extends FragmentActivity implements
        OnCheckedChangeListener {

    private RadioGroup rg_tab;
    //private ImageView iv_add;
    private FragmentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public void onBackPressed() {
        super.onBackPressed();
        //this.finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    };
*/
}
