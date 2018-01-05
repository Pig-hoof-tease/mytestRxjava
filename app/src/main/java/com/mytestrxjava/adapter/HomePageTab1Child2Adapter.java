package com.mytestrxjava.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mytestrxjava.R;

import java.util.List;

import bean.NewsItem;

/**
 * Created by Yomoo on 2017/9/7.
 */

public class HomePageTab1Child2Adapter extends BaseQuickAdapter<NewsItem, BaseViewHolder> {

    public HomePageTab1Child2Adapter(@LayoutRes int layoutResId, @Nullable List<NewsItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsItem item) {
        helper.setText(R.id.tv_hp_tab1_child_title, item.getTitle());
        helper.setText(R.id.tv_hp_tab1_child_content, item.getContent());
        helper.setText(R.id.tv_hp_tab1_child_time, item.getDate());
        Glide.with(mContext)
                .load(item.getImgLink())
                .asBitmap()
                .into((ImageView) helper.getView(R.id.iv_hp_tab1_child1_item));
    }

}
