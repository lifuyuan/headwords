package com.niuti.fuyuan.headwords;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by fuyuan on 2015/7/2.
 */
public class Config {

    public static final String KEY_TOKEN = "token";
    public static final String KEY_CODE = "code";
    public static final String KEY_PHONE = "phone";
    public static final String APP_ID = "com.niuti.fuyuan.headwords";
    public static final String CHARSET = "utf-8";
    public static final String HOME_URL = "http://121.40.201.41:5000/android/learnings/new_learning";
    public static final String USER_URL = "http://121.40.201.41:5000/android/learnings/learning_info";
    public static final String SET_URL = "http://121.40.201.41:5000/android/learnings/settings";
    public static final String LOGIN_URL = "http://121.40.201.41:5000/api/users/sign_in.json";
    public static final String SIGNUP_URL = "http://121.40.201.41:5000/api/users/sign_up.json";
    public static final String GETCODE_URL = "http://121.40.201.41:5000/api/users/code.json";
    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public static void cacheToken(Context context, String token) {
        Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }

    public static String getCachedCode(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_CODE, null);
    }

    public static void cacheCode(Context context, String code) {
        Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_CODE, code);
        e.commit();
    }

    public static String getCachedPhone(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_PHONE, null);
    }

    public static void cachePhone(Context context, String phone) {
        Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_PHONE, phone);
        e.commit();
    }
}
