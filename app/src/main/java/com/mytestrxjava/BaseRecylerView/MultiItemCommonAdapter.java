package com.mytestrxjava.BaseRecylerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Yomoo on 2017/8/25.
 */

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas, MultiItemTypeSupport multiTiemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiTiemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public RCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int getLayoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        RCViewHolder holder = RCViewHolder.get(mContext, parent, getLayoutId);
        return holder;
    }

}
