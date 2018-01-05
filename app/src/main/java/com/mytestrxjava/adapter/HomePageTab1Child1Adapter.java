package com.mytestrxjava.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.mytestrxjava.R;
import com.mytestrxjava.jsoup.bean.MJTTH5MovieInfo;

import java.util.List;

import bean.MeiJuBean;
import bean.NewsItem;
import bean.TTMJHomeBean;

/**
 * Created by Yomoo on 2017/9/7.
 */

public class HomePageTab1Child1Adapter extends BaseQuickAdapter<MJTTH5MovieInfo,BaseViewHolder>
{

    public HomePageTab1Child1Adapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<MJTTH5MovieInfo>() {
            @Override
            protected int getItemType(MJTTH5MovieInfo newsItem) {
//               if (TextUtils.isEmpty(newsItem.getImgUrl())){
//                   return 0;
//               }else{
                   return 1;
//               }
            }
        });
        getMultiTypeDelegate().registerItemType(0,R.layout.home_movie_item_view).registerItemType(1,R.layout.home_movie_item_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MJTTH5MovieInfo item) {
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.item_movie_name, item.getMovieName());
//                helper.setText(R.id.tv_hp_tab1_child_content, item.get());
//                helper.setText(R.id.tv_hp_tab1_child_time, item.getThumbnailContent());

                break;
            case 1:

                helper.setText(R.id.item_movie_name, item.getMovieName());
                helper.setText(R.id.itme_movie_state, item.getCurrenttate());
//                helper.setText(R.id.tv_hp_tab1_child_time, item.getDate());
                Glide.with(mContext)
                        .load(item.getThumbNailUrl())
                        .asBitmap()
                        .into((ImageView) helper.getView(R.id.itme_movie_img));
                break;
        }

    }

}
