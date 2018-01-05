package com.mytestrxjava.activity.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mytestrxjava.R;
import com.mytestrxjava.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yomoo on 2017/9/19.
 */

public abstract class RxBaseActivity extends RxAppCompatActivity implements OnClickListener {
    private String TAG=getClass().getSimpleName();
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //通过接口来设置布局界面
        setContentView(setLayoutId());
        //初始化黄油刀控件
        bind= ButterKnife.bind(this);
        //初始化Log
        LogUtils.setLogTag(TAG);
        //初始化状态栏
        initToolBar();
        //初始化视图界面
        initView(savedInstanceState);


    }

    /**
     * 设置布局layout
     * @return
     */
    public abstract int setLayoutId();

    /**
     * 初始化视图
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化头部
     */
    public abstract void initToolBar();

    /**
     * 基础头部
     */
    public  void initBaseToolbar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final Toolbar linear_bar = (Toolbar) findViewById(R.id.toolbar);
            final int statusHeight = getStatusBarHeight();
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = linear_bar.getHeight();
                    ViewGroup.LayoutParams params = ( ViewGroup.LayoutParams) linear_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    linear_bar.setLayoutParams(params);
                    if (linear_bar != null) {
                        //将Toolbar显示到界面
                        setSupportActionBar(linear_bar);
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                    }
                }
            });
        }
    };

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
    /**
     * 加载数据
     */
    public void loadData(){}

    /**
     * 初始化recyclerView
     */
    public void initRecyclerView(){}

    /**
     * 初始化refreshLayout
     */
    public void initRefreshLayout(){}

    /**
     * 设置数据显示
     */
    public void finishTask(){}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
