package com.mytestrxjava.BaseRecylerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yomoo on 2017/8/24.
 */

public class RCViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public RCViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static RCViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        RCViewHolder holder = new RCViewHolder(context, itemView, parent);
        return holder;
    }

    /**
     * g根据ViewId来获取View
     *
     * @param ViewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int ViewId) {
        View view = mViews.get(ViewId);
        if (view == null) {
            view = mConvertView.findViewById(ViewId);
            mViews.put(ViewId, view);
        }
        return (T) view;
    }

    public RCViewHolder setOnClickListener(int ViewId, View.OnClickListener listener) {
        View view = getView(ViewId);
        view.setOnClickListener(listener);
        return this;
    }
}
