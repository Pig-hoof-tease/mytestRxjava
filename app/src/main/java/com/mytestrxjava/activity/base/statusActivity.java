package com.mytestrxjava.activity.base;

/**
 * Created by Yomoo on 2017/8/29.
 */

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mytestrxjava.R;

import java.lang.reflect.Field;

public abstract class statusActivity extends superActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setStatusBar();
    }

    /**
     * 设置沉浸式状态栏
     * 这种方式不同于注释的哪种方式
     * 1、这种是拉大toolbar的高度达到沉浸式，但是这样toolbar的内容就会上移，所以需要给toolbar设置  android:paddingTop="22dp"
     * 2、注释的这种方式是给toolbar上面添加一个和toolbar颜色一样状态栏，达到占用的目的
     * 第一种方式当使用侧滑界面的时候因为侧滑界面上面没东西，所以拉大toolbar的话和下面的颜色一样，刚好达到我们的目的
     * 第二种方式当使用侧滑界面的时候会在侧滑界面顶部生成一个和toolgbar颜色一致的状态栏，导致和侧滑界面颜色不一致
     * 所以我恩采用第一种
     */

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup linear_bar = (ViewGroup) findViewById(R.id.toolbar);
            final int statusHeight = getStatusBarHeight();
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = linear_bar.getHeight();
                    ViewGroup.LayoutParams params = ( ViewGroup.LayoutParams) linear_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    linear_bar.setLayoutParams(params);
                }
            });
        }
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}