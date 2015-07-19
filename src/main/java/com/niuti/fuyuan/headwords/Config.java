package com.niuti.fuyuan.headwords;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by fuyuan on 2015/7/2.
 */
public class Config {

    public static final String KEY_TOKEN = "token";
    public static final String APP_ID = "com.niuti.fuyuan.headwords";
    public static final String CHARSET = "utf-8";
    public static final String SERVER_URL = "121.40.201.41";

    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public static void cacheToken(Context context, String token) {
        Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }
}
