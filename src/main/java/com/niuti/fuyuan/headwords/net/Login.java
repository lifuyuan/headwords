package com.niuti.fuyuan.headwords.net;

import android.text.TextUtils;

import com.niuti.fuyuan.headwords.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fuyuan on 2015/7/19.
 */
public class Login {
    public Login(String username,String password,final SuccessCallback successCallback,final FailCallback failCallback) {
        new NetConnection("http://121.40.201.41/api/users/sign_in.json", HttpMethod.POST, new NetConnection.SuccessCallback() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    if (!TextUtils.isEmpty(obj.getString(Config.KEY_TOKEN))){
                        if (successCallback!=null) {
                            successCallback.onSuccess(obj.getString(Config.KEY_TOKEN));
                        }
                    } else {
                        if (failCallback!=null) {
                            failCallback.onFail();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    if (failCallback!=null) {
                        failCallback.onFail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {

            @Override
            public void onFail() {
                if (failCallback!=null) {
                    failCallback.onFail();
                }
            }
        }, "user[name]",username,"user[password]",password);
    }

    public static interface SuccessCallback{
        void onSuccess(String token);
    }

    public static interface FailCallback{
        void onFail();
    }
}
