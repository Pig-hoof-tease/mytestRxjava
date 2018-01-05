package com.mytestrxjava.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.BaseFragment;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * Created by Yomoo on 2017/8/30.
 */

public class HomePageTwoFragment extends BaseFragment {

    @Override
    public int getLayoutResId() {
        return R.layout.home_page_two;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
