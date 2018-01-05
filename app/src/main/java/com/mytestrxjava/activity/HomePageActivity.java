package com.mytestrxjava.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.BaseFragment;
import com.mytestrxjava.activity.base.RxBaseActivity;
import com.mytestrxjava.activity.base.ToolBarActivity;
import com.mytestrxjava.utils.LogUtils;

import butterknife.BindView;

/**
 * Created by Yomoo on 2017/8/29.
 */

public class HomePageActivity extends RxBaseActivity{
    @BindView(R.id.ll_main_tab_1)
    LinearLayout main_tab_1;
    @BindView(R.id.ll_main_tab_2)
    LinearLayout main_tab_2;
    @BindView(R.id.ll_main_tab_3)
    LinearLayout main_tab_3;
    private HomePageOneFragment PageOne;
    private HomePageTwoFragment PageTwo;
    private HomePageThreeFragment PageThree;
    private BaseFragment mCurrentFragment;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setStatusBar();
////        setToolBarTitle("首页");
////        getSubTitle().setVisibility(View.GONE);
////        getSub2Title().setVisibility(View.GONE);
////        setShowBackBtn(false);
//
//    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        main_tab_1.setOnClickListener(this);
        main_tab_2.setOnClickListener(this);
        main_tab_3.setOnClickListener(this);
        stateCheck(savedInstanceState);
        setDefaultFragment();
    }

    @Override
    public void initToolBar() {
//            initBaseToolbar();
    }



    /**
     * 设置默认展示页面
     */
    private void setDefaultFragment() {
        PageOne = new HomePageOneFragment();
        mCurrentFragment = PageOne;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fm_home_page_main, mCurrentFragment, "" + R.id.ll_main_tab_1).show(mCurrentFragment).commit();
    }

    /**
     * 切换界面
     *
     * @param tab 需要切换的Tab
     */
    private void switchFragment(int tab) {

        switch (tab) {
            case R.id.ll_main_tab_1:
                if (PageOne == null) {
                    PageOne = new HomePageOneFragment();
                }
                switchContent(PageOne, R.id.ll_main_tab_1);
//                setToolBarTitle("首页");
                break;
            case R.id.ll_main_tab_2:
                if (PageTwo == null) {
                    PageTwo = new HomePageTwoFragment();
                }
                switchContent(PageTwo, R.id.ll_main_tab_2);
//                setToolBarTitle("页面2");
                break;
            case R.id.ll_main_tab_3:
                if (PageThree == null) {
                    PageThree = new HomePageThreeFragment();
                }
                switchContent(PageThree, R.id.ll_main_tab_3);
//                setToolBarTitle("我的");
                break;
        }
    }

    public void switchContent(BaseFragment to, int position) {
//        FragmentManager fm=getFragmentManager();
//        LogUtils.dLog("当前展示tab"+mCurrentFragment.getClass().getSimpleName()+"--替换页面tab"+to.getClass().getSimpleName());
        if (mCurrentFragment != to) {
            LogUtils.dLog("进入切换");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mCurrentFragment)
                        .add(R.id.fm_home_page_main, to, "" + position).show(to).commit(); // 隐藏当前的fragment，add下一个到Activity中
                LogUtils.dLog("添加切换");
            } else {
                transaction.hide(mCurrentFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
                LogUtils.dLog("直接切换");
            }
        } else {
            LogUtils.dLog("不用切换");
        }
        mCurrentFragment = to;
    }

    /**
     * 状态检测 用于内存不足的时候保证fragment不会重叠
     *
     * @param savedInstanceState
     */
    private void stateCheck(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

        } else {
            PageOne = (HomePageOneFragment) getSupportFragmentManager()
                    .findFragmentByTag("" + R.id.ll_main_tab_1);
            PageTwo = (HomePageTwoFragment) getSupportFragmentManager()
                    .findFragmentByTag("" + R.id.ll_main_tab_2);
            PageThree = (HomePageThreeFragment) getSupportFragmentManager()
                    .findFragmentByTag("" + R.id.ll_main_tab_3);
            setDefaultFragment();
        }
    }

    @Override
    public void onClick(View v) {
//        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_main_tab_1:
                switchFragment(R.id.ll_main_tab_1);
                break;
            case R.id.ll_main_tab_2:
                switchFragment(R.id.ll_main_tab_2);
                break;
            case R.id.ll_main_tab_3:
                switchFragment(R.id.ll_main_tab_3);
                break;
        }
    }
}
