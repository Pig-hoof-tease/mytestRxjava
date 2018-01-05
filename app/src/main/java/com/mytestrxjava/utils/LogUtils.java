package com.mytestrxjava.utils;


import android.text.TextUtils;
import android.util.Log;

import com.mytestrxjava.ConstantsConfigs;

public class LogUtils {

    private static String TAG = "未设置TAG";

    public static void setLogTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            TAG = tag;
        } else {
            TAG = "未设置TAG";
        }
    }

    public static void vLog(String msg) {
        if (ConstantsConfigs.showVLog) {
            Log.v(TAG, msg);
        }
    }

    public static void dLog(String msg) {
        if (ConstantsConfigs.showDLog) {
            Log.d(TAG, msg);
        }
    }

    public static void iLog(String msg) {
        if (ConstantsConfigs.showILog) {
            Log.i(TAG, msg);
        }
    }

    public static void wLog(String msg) {
        if (ConstantsConfigs.showWLog) {
            Log.w(TAG, msg);
        }
    }

    public static void eLog(String msg) {
        if (ConstantsConfigs.showELog) {
            Log.e(TAG, msg);
        }
    }
}

