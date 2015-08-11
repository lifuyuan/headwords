package com.niuti.fuyuan.headwords.net;

import android.text.TextUtils;

import com.niuti.fuyuan.headwords.Config;
import com.niuti.fuyuan.headwords.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fuyuan on 2015/7/19.
 */
public class TokenVerify {
    protected String TAG;
    public TokenVerify(String token, final SuccessCallback successCallback, final FailCallback failCallback) {
        new NetConnection(Config.TOKEN_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    Logger.show(TAG, "TokenResultValue:" + obj.getString("result"));
                    Logger.show(TAG, "TokenResult:" + obj.getString("result").equals("true"));
                    if (!TextUtils.isEmpty(obj.getString("result")) && obj.getString("result").equals("true")){
                        if (successCallback!=null) {
                            successCallback.onSuccess(obj.getString("result"));
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
        }, "token",token);
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
