package com.mytestrxjava.activity;

import android.os.Bundle;
import android.view.View;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.RxBaseActivity;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.trello.rxlifecycle2.components.RxActivity;

import org.jetbrains.annotations.NotNull;

import java.net.URL;

import butterknife.BindView;

/**
 * Created by Yomoo on 2017/11/8.
 */

public class WelComeActivity extends RxBaseActivity{
    @BindView(R.id.SVGA_WelCome)
    SVGAImageView imageView;
    @Override
    public int setLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        imageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                    finish();
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {

            }
        });


    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {

    }
}
