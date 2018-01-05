package com.mytestrxjava.BaseRecylerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Yomoo on 2017/8/24.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<RCViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = datas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public RCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RCViewHolder holder = RCViewHolder.get(mContext, parent, mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(RCViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(RCViewHolder holder, T t);

    public void addData(int postion, T data) {
        mDatas.add(postion, data);
        notifyItemInserted(postion);
    }

    public void removeData(int postion) {
        mDatas.remove(postion);
        notifyItemRemoved(postion);
    }

    public void setData(List<T> datas) {
        this.mDatas = datas;
    }
}
