package com.mytestrxjava.activity;

import android.os.Bundle;
import android.view.View;

import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.RxBaseActivity;

import butterknife.BindView;
import tcking.github.com.giraffeplayer2.VideoView;

/**
 * Created by Yomoo on 2017/10/10.
 */

public class GiraffePlayerActivity extends RxBaseActivity
{
    @BindView(R.id.video_view)
    VideoView mVideoView;
    String videoUri = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4" ;
    @Override
    public int setLayoutId() {
        return R.layout.activity_giraffe_player;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        VideoView videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoPath(videoUri).getPlayer().start();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {

    }
}
