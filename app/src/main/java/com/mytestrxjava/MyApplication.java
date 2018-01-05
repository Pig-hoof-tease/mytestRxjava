package com.mytestrxjava;

import android.app.Application;
import android.content.Context;

import com.mytestrxjava.utils.SharePreferenceUtil;

public class MyApplication extends Application {

    protected static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharePreferenceUtil.initSharePreferenceUtil(instance.getApplicationContext());
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
