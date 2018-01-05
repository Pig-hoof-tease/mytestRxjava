package com.mytestrxjava.BaseRecylerView;

/**
 * Created by Yomoo on 2017/8/25.
 */

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int postion, T t);
}
