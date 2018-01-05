package com.mytestrxjava.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.home.HomePagerTabOneChildPage1;
import com.mytestrxjava.activity.home.HomePagerTabOneChildPage2;
import com.mytestrxjava.activity.home.HomePagerTabOneChildPage3;
import com.mytestrxjava.activity.home.HomePagerTabOneChildPage4;

/**
 * Created by Yomoo on 2017/9/5.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES;
    private Fragment[] fragments;


    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.sections);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            //根据position实例化需要展示的界面
            switch (position) {
                case 0:
                    fragments[position] = HomePagerTabOneChildPage1.newIntance();
                    break;
                case 1:
                    fragments[position] = HomePagerTabOneChildPage2.newIntance();
                    break;
                case 2:
                    fragments[position] = HomePagerTabOneChildPage3.newIntance();
                    break;
                case 3:
                    fragments[position] = HomePagerTabOneChildPage4.newIntance();
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
