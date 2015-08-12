package com.niuti.fuyuan.headwords.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.niuti.fuyuan.headwords.BaseActivity;
import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.R;
import com.niuti.fuyuan.headwords.application.MyApplication;
import com.niuti.fuyuan.headwords.net.GetVersion;
import com.niuti.fuyuan.headwords.net.TokenVerify;
import com.niuti.fuyuan.headwords.utils.Logger;
import com.niuti.fuyuan.headwords.utils.ToastUtils;

/**
 * Created by fuyuan on 2015/7/2.
 */
public class SplashActivity extends BaseActivity {
    private static final int WHAT_INTENT2LOGIN = 1;
    private static final int WHAT_INTENT2MAIN = 2;
    private static final long SPLASH_DUR_TIME = 3000;
    private String local_version ;
    private String server_version = "1.0";

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case WHAT_INTENT2LOGIN:
                    intent2Activity(LoginActivity.class);
                    finish();
                    break;
                case WHAT_INTENT2MAIN:
                    intent2Activity(MainActivity.class);
                    finish();
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_splash);
        new GetVersion(new GetVersion.SuccessCallback() {
            @Override
            public void onSuccess(String version) {
                server_version = version;
                Logger.show(TAG, "server_version_1:" + server_version);
            }
        }, new GetVersion.FailCallback() {
            @Override
            public void onFail() {
                server_version = "1.0";
            }
        });
        Logger.show(TAG, "server_version_2:" + server_version);
        try {
            local_version = getVersionName();
            Logger.show(TAG, "local_version_1:" + local_version);
        } catch (Exception e) {
            e.printStackTrace();
            local_version = server_version;
        }
        Logger.show(TAG, "local_version_2:" + local_version);
        String token = Config.getCachedToken(this);
        if(token!=null && !token.equals("")) {
            new TokenVerify(token, new TokenVerify.SuccessCallback() {
                @Override
                public void onSuccess(String result) {
                    handler.sendEmptyMessageDelayed(WHAT_INTENT2MAIN, SPLASH_DUR_TIME);
                }
            }, new TokenVerify.FailCallback() {
                @Override
                public void onFail() {
                    ToastUtils.showToast(SplashActivity.this, "令牌过期，请您重新登录！", Toast.LENGTH_SHORT);
                    handler.sendEmptyMessageDelayed(WHAT_INTENT2LOGIN, SPLASH_DUR_TIME);
                }
            });
        } else {
            handler.sendEmptyMessageDelayed(WHAT_INTENT2LOGIN, SPLASH_DUR_TIME);
        }
    }

    private String getVersionName() throws Exception{
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }
}
