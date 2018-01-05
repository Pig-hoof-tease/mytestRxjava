package com.mytestrxjava.activity.home;

import android.os.Bundle;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.BaseFragment;

/**
 * Created by Yomoo on 2017/9/5.
 */

public class HomePagerTabOneChildPage3 extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_page_tab1_child_3;
    }

    public static HomePagerTabOneChildPage3 newIntance() {
        return new HomePagerTabOneChildPage3();
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
