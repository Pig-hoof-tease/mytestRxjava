package com.mytestrxjava.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mytestrxjava.DividerLine;
import com.mytestrxjava.Event.AppBarStateChangeEvent;
import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.RxBaseActivity;
import com.mytestrxjava.adapter.HomePageTab1Child1Adapter;
import com.mytestrxjava.adapter.VideoInfoAdapter;
import com.mytestrxjava.entity.AliAddrsBean;
import com.mytestrxjava.entity.BaseEntity;
import com.mytestrxjava.http.BaseObserver;
import com.mytestrxjava.http.RetrofitFactory;
import com.mytestrxjava.http.RxSchedulers;
import com.mytestrxjava.jsoup.MJTTH5BIZ;
import com.mytestrxjava.jsoup.bean.MJTTH5HomeData;
import com.mytestrxjava.jsoup.bean.MJTTH5MovieInfo;
import com.mytestrxjava.utils.APPIsInsert;
import com.mytestrxjava.utils.DisplayUtil;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.SystemBarHelper;
import com.mytestrxjava.utils.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

import TTMJ.TTMJHomeData;
import bean.TTMJVideoInfo;
import bean.TVEpisodes;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableLastMaybe;
import io.reactivex.schedulers.Schedulers;

import vite.rxbus.ThreadType;

import static com.mytestrxjava.R.mipmap.back;

/**
 * Created by Yomoo on 2017/9/21.
 */

public class VideosInfoActivity extends RxBaseActivity{
    @BindView(R.id.iv_video_cover)
    ImageView videoCover;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ab_video_top)
    AppBarLayout mAblAppBar;
    @BindView(R.id.tv_video_text_introduction)
    TextView videoIntroduction;
    @BindView(R.id.rv_video_drama)
    RecyclerView mRecylerView;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    private VideoInfoAdapter videoAdapter;
//    private TTMJHomeData HomeDataClient =new TTMJHomeData();
//    private TTMJVideoInfo VideoInfo;
    private MJTTH5BIZ MJTTClient =new MJTTH5BIZ();
    private MJTTH5MovieInfo VideoInfo;
    private  List<TVEpisodes> videosList;

    private String pageUrl;
    private List<TVEpisodes> mData;
    @Override
    public int setLayoutId() {
        return R.layout.activity_video_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        pageUrl=getIntent().getExtras().getString("pageUrl");
        loadData();
        initRecyclerView();
    }

    @Override
    public void initRefreshLayout() {
        super.initRefreshLayout();
        tv_title.setText(VideoInfo.getMovieName());
//        mToolbar.setTitle(VideoInfo.getVideoName());
        videoIntroduction.setText("\u3000\u3000"+VideoInfo.getMovieInfo());
        Glide.with(getApplicationContext())
                .load(VideoInfo.getThumbNailUrl())
                .asBitmap()
                .into(videoCover);
        videoAdapter.setNewData(mData);
        videoAdapter.notifyDataSetChanged();
    }

    private void login(String userId, String password) {
        Observable<BaseEntity<String>> observable = RetrofitFactory.getInstance().login(userId, password);
        observable.compose(RxSchedulers.<BaseEntity<String>>compose()).subscribe(new BaseObserver<String>(getApplicationContext()) {
            @Override
            protected void onHandleSuccess(String s) {
                ToastUtil.showToastShort("请求成功"+s);
            }
        });


    }
    private void test(){
        Observable<AliAddrsBean> observable =RetrofitFactory.getInstance().getIndexContentOne();
        observable.compose(RxSchedulers.<AliAddrsBean>compose()).subscribe(new Observer<AliAddrsBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
             LogUtils.dLog("onSubscribe请求"+d.toString());
            }

            @Override
            public void onNext(@NonNull AliAddrsBean s) {
                LogUtils.dLog("onNext请求"+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtils.dLog("onError请求"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.dLog("onComplete请求");
            }
        });

    }




    @Override
    public void initToolBar() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("Rxjava-onNext");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.dLog("11223344"+s);
            }
        });
        Observable<String> obj =Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("dddd");
            }
        });
        obj.compose(this.bindUntilEvent(ActivityEvent.DESTROY));
        pageUrl=getIntent().getExtras().getString("pageUrl");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        //设置收缩后Toolbar上字体的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, mToolbar);
//        mToolbar.setTitle(VideoInfo.getVideoName());
        mAblAppBar.addOnOffsetChangedListener(new AppBarStateChangeEvent() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {
                if (state==State.EXPANDED){
                    //展开状态
                    tv_title.setVisibility(View.VISIBLE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideosInfoActivity.this, 15), 0);
                }else if (state==State.COLLAPSED){
                    tv_title.setVisibility(View.VISIBLE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideosInfoActivity.this, 15), 0);
                }else{
                    tv_title.setVisibility(View.GONE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideosInfoActivity.this, 15), 0);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void loadData() {
        super.loadData();
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {

                if (!TextUtils.isEmpty(pageUrl)){
                    LogUtils.dLog("videoInfo----url:"+pageUrl);
                    e.onNext(MJTTClient.getMovieInfo(pageUrl));

                }

            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o!=null){
                        VideoInfo =(MJTTH5MovieInfo) o;
                        mData=VideoInfo.getMovieNum();
                        initRefreshLayout();
                        }
                    }
                });
    }

    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
//        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
//        dividerLine.setSize(1);
//        dividerLine.setColor(0xFFEEEEEE);
//        mRecylerView.addItemDecoration(dividerLine);
        mRecylerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        videoAdapter= new VideoInfoAdapter(R.layout.activity_video_info_item,mData);
        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (APPIsInsert.isAppInstalled(getApplicationContext(),"com.xunlei.downloadprovider")){
                    String link = videoAdapter.getData().get(position).getTVEpisodesUrl();
                    if (!TextUtils.isEmpty(link)){
                        LogUtils.dLog("下载链接---"+link);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        intent.addCategory("android.intent.category.DEFAULT");
                        startActivity(intent);
                    }else{
                        ToastUtil.showToastShort("暂无下载链接");
                    }
                }else{
                    ToastUtil.showToastShort("请安装迅雷");
                }



//                ToastUtil.showToastLong("点击了item");
//                RxBus.post("VideosInfoActivity",mDatas.get(position));
//                Intent in =new Intent(getApplicationContext(), VideosInfoActivity.class);
//                in.putExtra("pageUrl",mData.get(position).getTVEpisodesUrl());
//                startActivity(in);
            }
        });

        mRecylerView.setAdapter(videoAdapter);
    }

}
