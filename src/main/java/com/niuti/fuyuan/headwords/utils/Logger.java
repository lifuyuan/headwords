package com.niuti.fuyuan.headwords.utils;

import android.util.Log;

import com.niuti.fuyuan.headwords.constants.CommonConstants;

/**
 * Created by fuyuan on 2015/6/24.
 */
public class Logger {

    /**
     * ��ʾLOG(Ĭ��info����)
     *
     * @param TAG
     * @param msg
     */
    public static void show(String TAG, String msg) {
        if (!CommonConstants.isShowLog) {
            return;
        }
        show(TAG, msg, Log.INFO);
    }

    /**
     * ��ʾLOG
     *
     * @param TAG
     * @param msg
     * @param level
     *            1-info; 2-debug; 3-verbose
     */
    public static void show(String TAG, String msg, int level) {
        if (!CommonConstants.isShowLog) {
            return;
        }
        switch (level) {
            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            default:
                Log.i(TAG, msg);
                break;
        }
    }
}
