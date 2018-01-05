package com.mytestrxjava.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.RxBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Yomoo on 2017/12/5.
 */

public class LoginBgActivity extends RxBaseActivity{
    @BindView(R.id.login_bg_image4)
    ImageView mLoginBg4;
    @BindView(R.id.login_bg_image3)
    ImageView mLoginBg3;
    @BindView(R.id.login_bg_image2)
    ImageView mLoginBg2;
    @BindView(R.id.login_bg_image1)
    ImageView mLoginBg1;

    private List<Integer> mImgList=new ArrayList<>();
    @Override
    public int setLayoutId() {
        return R.layout.activity_login_bg;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        loadData();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(getAnimator(0,1), getAnimator(1,2), getAnimator(2,3), getAnimator(3,0));
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 将放大的View 复位
//                mLoginBg.setScaleX(1.0f);
//                mLoginBg.setScaleY(1.0f);
                // 循环播放
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadData() {
        super.loadData();
        mImgList.clear();
        mImgList.add(R.mipmap.testain1);
        mImgList.add(R.mipmap.testain2);
        mImgList.add(R.mipmap.testain3);
        mImgList.add(R.mipmap.testain4);
    }

    private AnimatorSet getAnimator(int position1, int position2){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(getBitmapView(position1), "alpha", 1.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(getBitmapView(position2), "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale1 = ObjectAnimator.ofFloat(getBitmapView(position1), "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale2 = ObjectAnimator.ofFloat(getBitmapView(position1), "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.setDuration(5000);
        animatorSet1.play(animator1).with(animator2).with(animatorScale1).with(animatorScale2);
        return animatorSet1;
    }
    private ImageView getBitmapView(int type){

        switch (type){
            case 0:
                return mLoginBg1;
            case 1:
                return mLoginBg2;
            case 2:
                return mLoginBg3;
            case 3:
                return mLoginBg4;
        }
        return mLoginBg4;
    }


}
