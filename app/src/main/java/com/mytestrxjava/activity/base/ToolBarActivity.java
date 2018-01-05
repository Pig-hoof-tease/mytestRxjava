package com.mytestrxjava.activity.base;

/**
 * Created by Yomoo on 2017/8/29.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mytestrxjava.R;
import com.mytestrxjava.utils.LogUtils;

/**
 * ToolBarActivity.
 */
public abstract class ToolBarActivity extends statusActivity implements OnClickListener {
    private String TAG = getClass().getSimpleName();
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;
    private TextView mToolbarSub2Title;
    private boolean isShowBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

         /*
          toolbar.setLogo(R.mipmap.ic_launcher);
          toolbar.setTitle("Title");
          toolbar.setSubtitle("Sub Title");
          */
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_tv_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_tv_left);
        mToolbarSub2Title = (TextView) findViewById(R.id.toolbar_tv_right);
        getSubTitle().setVisibility(View.GONE);//隐藏副标题
        getSub2Title().setVisibility(View.GONE);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        LogUtils.setLogTag(TAG);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }

    /**
     * 获取副头部标题的TextView
     *
     * @return
     */
    public TextView getSub2Title() {
        return mToolbarSub2Title;
    }


    /**
     * 设置头部标题
     *
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return isShowBack;
    }

    public void setShowBackBtn(boolean show) {
        isShowBack = show;
    }

    @Override
    public void onClick(View v) {

    }
}

