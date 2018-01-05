package com.mytestrxjava.activity.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mytestrxjava.DividerLine;
import com.mytestrxjava.R;
import com.mytestrxjava.activity.TestVideosActivity;
import com.mytestrxjava.activity.VideosInfoActivity;
import com.mytestrxjava.activity.base.BaseFragment;
import com.mytestrxjava.adapter.HomePageTab1Child1Adapter;
import com.mytestrxjava.jsoup.MJTTH5BIZ;
import com.mytestrxjava.jsoup.bean.MJTTH5HomeData;
import com.mytestrxjava.jsoup.bean.MJTTH5MovieInfo;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import TTMJ.TTMJHomeData;
import bean.CommonException;
import bean.MeiJuBean;
import bean.TTMJHomeBean;
import butterknife.BindView;
import csdn.Constaint;
import meijukankan.MeiJuKanKan;

/**
 * Created by Yomoo on 2017/9/5.
 */

public class HomePagerTabOneChildPage1 extends BaseFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rc_home_tab1_child_1)
    RecyclerView mRecylerView;
    private int newsType = Constaint.NEWS_TYPE_YEJIE;
    private MJTTH5BIZ MJTTClient;
    private MJTTH5HomeData HomeData;
    private  List<MJTTH5MovieInfo> mDatas;
//    private int currentPage = 1;
    private HomePageTab1Child1Adapter multiItemCommonAdapter;
    private boolean moreRefresh=false;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_page_tab1_child_1;
    }

    public static HomePagerTabOneChildPage1 newIntance() {
        return new HomePagerTabOneChildPage1();
    }

    @Override
    public void finishCreateView(Bundle state) {
//        RxBus.register(getSupportActivity());
        lazyLoad();
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        RxBus.unregister(getSupportActivity());
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        loadData();

    }

    @Override
    protected void loadData() {
        super.loadData();
        MJTTClient = new MJTTH5BIZ();
        new LoadDatasTask().execute();

    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
//        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
//        dividerLine.setSize(1);
//        dividerLine.setColor(0xFFDDDDDD);
//        mRecylerView.addItemDecoration(dividerLine);
        mRecylerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        multiItemCommonAdapter=new HomePageTab1Child1Adapter();
        multiItemCommonAdapter.setNewData(mDatas);
        multiItemCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                LogUtils.dLog("点击了item");
//                ToastUtil.showToastLong("点击了item");
//                RxBus.post("VideosInfoActivity",mDatas.get(position));
                Intent in =new Intent(getApplicationContext(), VideosInfoActivity.class);
//                Intent in =new Intent(getApplicationContext(), TestVideosActivity.class);
                in.putExtra("pageUrl",mDatas.get(position).getMovieUrl());
                startActivity(in);
            }
        });

//        multiItemCommonAdapter.setEnableLoadMore(true);
//        multiItemCommonAdapter.setPreLoadNumber(1);
//        multiItemCommonAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                currentPage++;
//                moreRefresh=true;
//                loadData();
//            }
//        },mRecylerView);
        mRecylerView.setAdapter(multiItemCommonAdapter);
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
//                currentPage=1;
//                multiItemCommonAdapter.disableLoadMoreIfNotFullPage();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void RefreshAdapter() {

        if (mSwipeRefreshLayout==null){
            return;
        }
        if (mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
            multiItemCommonAdapter.setNewData(mDatas);
            multiItemCommonAdapter.notifyDataSetChanged();
            moreRefresh=false;

        }
//        if (moreRefresh){
//            for (int i=0;i<mDatas.size();i++){
//                multiItemCommonAdapter.addData(mDatas.get(i));
//            }
//           if (mDatas.size()>0){
//               multiItemCommonAdapter.loadMoreComplete();
//           }else{
//               multiItemCommonAdapter.loadMoreEnd();
//               moreRefresh=false;
//           }
//        }
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
               HomeData  = MJTTClient.MJTTH5HomeData();
                mDatas = HomeData.getMovieInfoList();
            } catch (CommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            multiItemCommonAdapter.setData(mDatas);
            RefreshAdapter();

//            initRecyclerView();
//            mAdapter.addAll(mDatas);
//            mAdapter.notifyDataSetChanged();
//            mXListView.stopRefresh();
        }

    }
    private void setHomeType(int type){
        if (type>=HomeData.getMenuList().size()){
            return;
        }

    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
