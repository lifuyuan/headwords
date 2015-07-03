package com.niuti.fuyuan.headwords.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fuyuan on 2015/6/30.
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * 显示Toast
     */
    public static void showToast(Context context, CharSequence text, int duration) {
        if(mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
