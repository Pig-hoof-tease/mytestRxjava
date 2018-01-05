package com.mytestrxjava.activity.home;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mytestrxjava.BaseRecylerView.CommonAdapter;
import com.mytestrxjava.BaseRecylerView.RCViewHolder;
import com.mytestrxjava.DividerLine;
import com.mytestrxjava.R;
import com.mytestrxjava.activity.AppbarLayoutTest;
import com.mytestrxjava.activity.TestVideosActivity;
import com.mytestrxjava.activity.base.BaseFragment;
import com.mytestrxjava.adapter.HomePageTab1Child2Adapter;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import bean.CommonException;
import bean.NewsItem;
import biz.NewsItemBiz;
import butterknife.BindView;
import csdn.Constaint;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by Yomoo on 2017/9/5.
 */

public class HomePagerTabOneChildPage2 extends BaseFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_hp_tab1_child2)
    RecyclerView mRecylcerView;
    HomePageTab1Child2Adapter mAdapter;
    private int newsType = Constaint.NEWS_TYPE_YIDONG;
    private List<NewsItem> mDatas = new ArrayList<>();
    private NewsItemBiz mNewsItemBiz;
    private int currentPage = 1;
    int mCurrentCounter = 0;
    int TOTAL_COUNTER = 100;
    boolean isErr = false;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_page_tab1_child_2;
    }

    public static HomePagerTabOneChildPage2 newIntance() {
        return new HomePagerTabOneChildPage2();
    }

    @Override
    public void finishCreateView(Bundle state) {
        initRecyclerView();
        initRefreshLayout();
//        loadData();
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        mRecylcerView.addItemDecoration(dividerLine);
        mRecylcerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new HomePageTab1Child2Adapter(R.layout.home_page_tab1_child1_item, mDatas);
        mRecylcerView.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setPreLoadNumber(2);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecylcerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.dLog("开始获取更多数据" + currentPage + 1);
                        currentPage++;
                        loadData();
                    }

                }, 1);
            }
        }, mRecylcerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.dLog("点击了item");
                ToastUtil.showToastLong("点击了item");
//                Intent in =new Intent(getApplicationContext(), TestVideosActivity.class);
//                startActivity(in);
               //跳转西瓜
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName("tv.danmaku.ijk.media.dem", "GiraffePlayerActivity");
                intent.setComponent(cn);
                startActivity(intent);

//                String link = "magnet:?xt=urn:sha1:YNCKHTQCWBTRNJIV4WNAE52SJU";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//                intent.addCategory("android.intent.category.DEFAULT");
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void initRefreshLayout() {
        super.initRefreshLayout();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.swiperefreshcolor);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                loadData();
            }
        });

    }

    private void RefreshAdapter() {
        if (mSwipeRefreshLayout==null){
            return;
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(mDatas);
            mAdapter.disableLoadMoreIfNotFullPage();
            mAdapter.notifyDataSetChanged();
            isErr=false;
            mCurrentCounter=0;
        }
        if (isErr&&!mSwipeRefreshLayout.isRefreshing()) {
            if (mCurrentCounter >= TOTAL_COUNTER) {
                //数据全部加载完毕
                mAdapter.loadMoreEnd();
            } else {

                if (isErr) {
                    //成功获取更多数据
                    LogUtils.dLog("成功获取更多数据" + mDatas.size());
                    for (int i = 0; i < mDatas.size(); i++) {
                        mAdapter.addData(mDatas.get(i));
                    }
                    mCurrentCounter = mAdapter.getData().size();
                  mAdapter.loadMoreComplete();
//                    mAdapter.loadMoreEnd();
                } else {
                    //获取更多数据失败
                    isErr = true;
//          Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                    mAdapter.loadMoreFail();
                }
            }
        }


    }

    @Override
    protected void loadData() {
        super.loadData();
        mNewsItemBiz = new NewsItemBiz();
        new LoadDatasTask().execute();
    }

    /**
     * 记载数据的异步任务
     *
     * @author zhy
     */
    class LoadDatasTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
                mDatas = newsItems;
            } catch (CommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            RefreshAdapter();
            isErr = true;
        }

    }
}
