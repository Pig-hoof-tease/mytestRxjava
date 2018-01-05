package com.mytestrxjava.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mytestrxjava.DividerLine;
import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.RxBaseActivity;
import com.mytestrxjava.activity.home.HomePagerTabOneChildPage2;
import com.mytestrxjava.adapter.HomePageTab1Child2Adapter;
import com.mytestrxjava.adapter.SearchAdapter;
import com.mytestrxjava.jsoup.MJTTH5BIZ;
import com.mytestrxjava.jsoup.bean.MJTTH5MovieInfo;
import com.mytestrxjava.jsoup.bean.MJTTH5TypePage;
import com.mytestrxjava.utils.KeyBoardUtil;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.ToastUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import bean.CommonException;
import bean.NewsItem;
import biz.NewsItemBiz;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yomoo on 2017/10/13.
 */

public class SearchActivity extends RxBaseActivity {
    @BindView(R.id.search_img)
    ImageView mSearchBtn;
    @BindView(R.id.search_edit)
    EditText mSearchEdit;
    @BindView(R.id.search_text_clear)
    ImageView mSearchTextClear;

    @BindView(R.id.rc_search_result)
    RecyclerView mSearchResultView;
    @BindView(R.id.iv_search_loading)
    ImageView mLoadingView;
    private MJTTH5BIZ MJTTClient;
    private AnimationDrawable mAnimationDrawable;
    private SearchAdapter mAdapter;
    private MJTTH5TypePage searchResultData;
    private List<MJTTH5MovieInfo> mDatas;
    private String query;
    private Boolean isLoadMore = false;
    private Boolean isLoadMoreSuccess = false;
    private int currentPage = 0;

    @Override
    public int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        query = getIntent().getExtras().getString("query");
        mSearchEdit.setText(query);
        mLoadingView.setImageResource(R.drawable.anim_search_loading);
        mAnimationDrawable = (AnimationDrawable) mLoadingView.getDrawable();
        showSearchAnim();
        initRecyclerView();
        loadData();
        setUpEditText();
        search();
    }

    @OnClick(R.id.search_back)
    void OnBack() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
        super.onBackPressed();
    }

    private void setUpEditText() {
        RxTextView.textChanges(mSearchEdit).compose(this.<CharSequence>bindToLifecycle())
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(@NonNull CharSequence charSequence) throws Exception {
                        return new StringBuilder(charSequence).reverse().toString();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (!TextUtils.isEmpty(s)) {
                            mSearchTextClear.setVisibility(View.VISIBLE);
                        } else {
                            mSearchTextClear.setVisibility(View.GONE);
                        }
                    }
                });
        RxView.clicks(mSearchTextClear)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mSearchEdit.setText("");
                    }
                });
        RxTextView.editorActions(mSearchEdit)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {

                        return !TextUtils.isEmpty(mSearchEdit.getText().toString().trim());
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        LogUtils.dLog("EditorInfo.IME_ACTION_SEARCH----"+(integer == EditorInfo.IME_ACTION_SEARCH));
                        return integer == EditorInfo.IME_ACTION_SEARCH;
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        return Observable.just(mSearchEdit.getText().toString().trim());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        KeyBoardUtil.closeKeybord(mSearchEdit, getApplicationContext());
                        showSearchAnim();
                        cleardDta();
                        query=s;
                        loadData();
                    }
                });
    }

    private void search() {
//    Function  s
        RxView.clicks(mSearchBtn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .map(new Function() {
                    @Override
                    public String apply(@NonNull Object o) throws Exception {
                        return mSearchEdit.getText().toString().trim();
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        KeyBoardUtil.closeKeybord(mSearchEdit, getApplicationContext());
                        showSearchAnim();
                        cleardDta();
                        query=s;
                        loadData();
                    }
                });
    }
private void cleardDta(){
    searchResultData=new MJTTH5TypePage();
    mDatas.clear();
    currentPage=0;
    isLoadMoreSuccess=false;
    isLoadMore=false;
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
    }

    @Override
    public void loadData() {
        super.loadData();
        MJTTClient = new MJTTH5BIZ();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {

                try {
                    if (!isLoadMore) {
                        searchResultData = MJTTClient.getTypeList(query, true);
                        mDatas = searchResultData.getMovieInfoList();
                    } else {
                        if (searchResultData.getPageList() != null && searchResultData.getPageList().size() > currentPage) {
                            searchResultData = MJTTClient.getTypeList(searchResultData.getPageList().get(currentPage).getPageUrl(), false);
                        } else {
                            //数据全部加载完毕
//                        mAdapter.loadMoreEnd();
                        }
                    }

                } catch (CommonException ee) {
                    // TODO Auto-generated catch block
                    ee.printStackTrace();
                }
                e.onNext(RESULT_OK);
            }
        })
                //这里类似观察对象是水里的鱼钩，观察者是岸上的人，鱼钩上钓到鱼了，观察者把鱼钩取到岸上，并把鱼取下
                //设置观察对象在io线程执行
                .subscribeOn(Schedulers.io())
                //设置观察者在主线程观察
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) throws Exception {
                        if (i==RESULT_OK){
                            isLoadMoreSuccess = true;
                            RefreshAdapter();
                        }

                    }
                });




//        new LoadDatasTask().execute();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        mSearchResultView.addItemDecoration(dividerLine);
        mSearchResultView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new SearchAdapter(R.layout.item_search_result, mDatas);
        mSearchResultView.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setPreLoadNumber(1);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mSearchResultView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.dLog("开始获取更多数据" + currentPage + 1);

                        isLoadMore = true;
                        loadData();
                    }

                }, 1);
            }
        }, mSearchResultView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.dLog("点击了item");
                ToastUtil.showToastLong("点击了item");
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.dLog("点击了item");
                Intent in =new Intent(getApplicationContext(), VideosInfoActivity.class);
                in.putExtra("pageUrl",mDatas.get(position).getMovieUrl());
                startActivity(in);
            }
        });

    }

    private void showSearchAnim() {
        mLoadingView.setImageResource(R.drawable.anim_search_loading);
        mSearchResultView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mAnimationDrawable.start();
    }

    private void hideSearchAnim() {
        mLoadingView.setVisibility(View.GONE);
        mSearchResultView.setVisibility(View.VISIBLE);
        mAnimationDrawable.stop();
    }

    public void setEmptyLayout() {
        mLoadingView.setVisibility(View.VISIBLE);
        mSearchResultView.setVisibility(View.GONE);
        mLoadingView.setImageResource(R.mipmap.search_failed);
    }

    private void RefreshAdapter() {
        hideSearchAnim();

        if (isLoadMore) {
            if (currentPage >= searchResultData.getPageList().size()) {
                //数据全部加载完毕

                if (mDatas.size() == 0) {
                    mAdapter.loadMoreEnd();
                    setEmptyLayout();
                } else {
                    mAdapter.loadMoreEnd();
                }

            } else {

                if (isLoadMoreSuccess) {
                    //成功获取更多数据
                    LogUtils.dLog("成功获取更多数据" + mDatas.size());
                    for (int i = 0; i < searchResultData.getMovieInfoList().size(); i++) {
                        mAdapter.addData(searchResultData.getMovieInfoList().get(i));
                    }
                    //游标指向下一页
                    currentPage++;
                    mAdapter.loadMoreComplete();
//                    isLoadMoreSuccess=false;
//                    mAdapter.loadMoreEnd();
                } else {
                    //获取更多数据失败
//                    isLoadMoreSuccess = true;
//          Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                    mAdapter.loadMoreFail();
                }
            }
        } else {
            if (mDatas != null && mDatas.size() > 0) {
                mAdapter.setNewData(mDatas);
                mAdapter.notifyDataSetChanged();
            } else {
                setEmptyLayout();
                mAdapter.loadMoreEnd();
            }
            mAdapter.disableLoadMoreIfNotFullPage();
        }
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
                if (!isLoadMore) {
                    searchResultData = MJTTClient.getTypeList(query, true);
                    mDatas = searchResultData.getMovieInfoList();
                } else {
                    if (searchResultData.getPageList() != null && searchResultData.getPageList().size() > currentPage) {
                        searchResultData = MJTTClient.getTypeList(searchResultData.getPageList().get(currentPage).getPageUrl(), false);
                    } else {
                        //数据全部加载完毕
//                        mAdapter.loadMoreEnd();
                    }
                }

            } catch (CommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            isLoadMoreSuccess = true;
            RefreshAdapter();
        }

    }
}
