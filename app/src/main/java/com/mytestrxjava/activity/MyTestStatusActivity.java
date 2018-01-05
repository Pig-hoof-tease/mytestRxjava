package com.mytestrxjava.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.RxBaseActivity;
import com.mytestrxjava.utils.SystemBarHelper;

import butterknife.BindView;

/**
 * Created by Yomoo on 2017/11/13.
 */

public class MyTestStatusActivity extends RxBaseActivity{
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_info);
//    }
@BindView(R.id.collapsing_toolbar)
CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    public int setLayoutId() {
        return R.layout.activity_video_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {
//        ActionBar supportActionBar = getSupportActionBar();
//        if (supportActionBar != null) {
//            supportActionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        //设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
//        //设置收缩后Toolbar上字体的颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
//        SystemBarHelper.setHeightAndPadding(this, mToolbar);
    }

    @Override
    public void onClick(View v) {

    }
}
