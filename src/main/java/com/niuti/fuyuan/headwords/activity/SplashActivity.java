package com.niuti.fuyuan.headwords.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fuyuan on 2015/7/2.
 */
public class SplashActivity extends BaseActivity {
    private static final int WHAT_INTENT2LOGIN = 1;
    private static final int WHAT_INTENT2MAIN = 2;
    private static final long SPLASH_DUR_TIME = 3000;

    public static final int SHOW_UPDATE_DIALOG = 3;
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
                case SHOW_UPDATE_DIALOG:
                    showUpdateDialog();
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
        new Thread(new CheckVersionTask()).start();
    }

    private String getVersionName() throws Exception{
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 检查网络是否有更新
     */
    private class CheckVersionTask implements Runnable{

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                URL url = new URL(Config.APK_URL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    try {
                        local_version = getVersionName();
                        Logger.show(TAG, "local_version_1:" + local_version);
                    } catch (Exception e) {
                        e.printStackTrace();
                        local_version = server_version;
                    }
                    Logger.show(TAG, "local_version_2:" + local_version);
                    new GetVersion(new GetVersion.SuccessCallback() {
                        @Override
                        public void onSuccess(String version) {
                            server_version = version;
                            Logger.show(TAG, "server_version_1:" + server_version);

                            if(server_version.equals(local_version)){
                                Logger.show(TAG, "SplashActivity.CheckVersionTask版本一致，显示主页面");
                                loadMain();
                            }else{
                                Logger.show(TAG, "SplashActivity.CheckVersionTask版本不致，显示升级提示窗口");
                                handler.sendEmptyMessageDelayed(SHOW_UPDATE_DIALOG, 0);
                            }
                        }
                    }, new GetVersion.FailCallback() {
                        @Override
                        public void onFail() {
                            Logger.show(TAG, "SplashActivity.CheckVersionTask服务器错误");
                            loadMain();
                        }
                    });

                }else{
                    // server error
                    Logger.show(TAG, "SplashActivity.CheckVersionTask服务器错误");
                    loadMain();
                }
            } catch (MalformedURLException e) {
                Logger.show(TAG, "SplashActivity.CheckVersionTask url路径错误");
                loadMain();
                e.printStackTrace();
            } catch (Resources.NotFoundException e) {
                Logger.show(TAG, "SplashActivity.CheckVersionTask url路径错误");
                loadMain();
                e.printStackTrace();
            } catch (IOException e) {
                Logger.show(TAG, "SplashActivity.CheckVersionTask 网络错误");
                loadMain();
                e.printStackTrace();
            }

            long dTime = System.currentTimeMillis() - startTime;
            if(dTime<2000){
                try {
                    Thread.sleep(2000-dTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    protected void showUpdateDialog() {
        AlertDialog.Builder builder = new Builder(this);

        builder.setTitle("更新提示");
        builder.setMessage("当前版本的app有更新");
        final ProgressDialog pd;
        pd = new  ProgressDialog(SplashActivity.this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyApplication.getInstance().exit();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                MyApplication.getInstance().exit();
            }
        });

        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    Logger.show(TAG, "SplashActivity.下载apk:" + Config.APK_URL);
                    String fileName = "cloze"+server_version+".apk";
                    File downFile = new File(Environment.getExternalStorageDirectory(), fileName);
                    Logger.show(TAG, "SplashActivity.保存apk:" + downFile.getPath());
                    FinalHttp finalHttp = new FinalHttp();
                    finalHttp.download(Config.APK_URL, downFile.getAbsolutePath(), new AjaxCallBack<File>() {

                        @Override
                        public void onFailure(Throwable t, String strMsg) {
                            super.onFailure(t, strMsg);
                            pd.dismiss();
                            ToastUtils.showToast(SplashActivity.this, "下载失败", Toast.LENGTH_SHORT);
                            loadMain();
                            if(t != null) t.printStackTrace();
                        }

                        @Override
                        public void onLoading(long count, long current) {
                            super.onLoading(count, current);
                            pd.setMessage("正在下载更新");
                            pd.show();
                            //int progress = (int) (current*100/count);
                            //tv_splash_state.setText("downloading... "+ progress+"%");
                        }

                        @Override
                        public void onSuccess(File t) {
                            super.onSuccess(t);
                            Logger.show(TAG, "SplashActivity.下载完成apk:" + t.getPath());
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            // Uri 格式化工具
                            intent.setDataAndType(Uri.fromFile(t), "application/vnd.android.package-archive");
                            startActivity(intent);
                            pd.dismiss();
                            //下载完成后要关闭原来的splash
                            finish();
                        }
                    });
                }else{
                    ToastUtils.showToast(SplashActivity.this, "sd卡不可用,更新失败", Toast.LENGTH_SHORT);
                    loadMain();
                }
            }
        });
        builder.show();
    }

    private void loadMain() {
        String token = Config.getCachedToken(SplashActivity.this);
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
}
