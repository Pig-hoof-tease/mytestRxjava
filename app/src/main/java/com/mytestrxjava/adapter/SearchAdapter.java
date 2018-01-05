package com.mytestrxjava.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mytestrxjava.R;
import com.mytestrxjava.jsoup.bean.MJTTH5MovieInfo;

import java.util.List;

/**
 * Created by Yomoo on 2017/10/16.
 */

public class SearchAdapter extends BaseQuickAdapter<MJTTH5MovieInfo,BaseViewHolder> {

    public SearchAdapter(@LayoutRes int layoutResId, @Nullable List<MJTTH5MovieInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MJTTH5MovieInfo item) {
        helper.setText(R.id.tv_search_result_movie_name, item.getMovieName());
        helper.setText(R.id.tv_search_result_movie_oldname, item.getOldName());
        helper.setText(R.id.tv_search_result_movie_state, item.getCurrenttate());
        helper.setText(R.id.tv_search_result_movie_hot, item.getMovieHot());
        Glide.with(mContext)
                .load(item.getThumbNailUrl())
                .asBitmap()
                .into((ImageView) helper.getView(R.id.tv_search_result_movie_img));
    }
}
