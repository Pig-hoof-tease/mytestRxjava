package com.mytestrxjava.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mytestrxjava.R;
import com.mytestrxjava.utils.XGUtils.StringUtils;

import java.util.List;

import bean.TVEpisodes;

/**
 * Created by Yomoo on 2017/9/30.
 */

public class VideoInfoAdapter extends BaseQuickAdapter<TVEpisodes,BaseViewHolder>{

    public VideoInfoAdapter(@LayoutRes int layoutResId, @Nullable List<TVEpisodes> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TVEpisodes item) {
        //这里自动换行有问题，转成全角
       helper.setText(R.id.tv_video_text_item,(item.getTVEpisodesNum()));
    }
}
