package com.mytestrxjava.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.BaseFragment;
import com.mytestrxjava.adapter.HomePagerAdapter;
import com.mytestrxjava.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by Yomoo on 2017/8/30.
 */

public class HomePageOneFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp_hp_tab1)
    ViewPager mViewPager;
    @BindView(R.id.tl_hp_top_tab_indicator)
    TabLayout mTabIndicator;
    @BindView(R.id.toolbar_user_avatar)
    ImageView avater;
//    @BindView(R.id.cd_home_search_btn)
//    CardView searchBtn;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    public static HomePageOneFragment newIntance() {
        return new HomePageOneFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_page_one;
    }

    @Override
    public void finishCreateView(Bundle state) {
        setHasOptionsMenu(true);
        initToolbar();
        initSearchView();
        initViewPager();
//        registerForContextMenu(avater);
//        avater.showContextMenu();
//        isOpenSearchView();
    }

    private void initViewPager() {
        String[] array = getApplicationContext().getResources().getStringArray(R.array.sections);
        for (int i = 0; i < array.length; i++) {
            mTabIndicator.addTab(mTabIndicator.newTab().setText(array[i]));
        }
        HomePagerAdapter mHomeAdapter = new HomePagerAdapter(getChildFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(mHomeAdapter);
        mViewPager.setCurrentItem(0);
        mTabIndicator.setupWithViewPager(mViewPager);


    }
    private  void initToolbar() {
        mToolbar.setTitle("");
        ((HomePageActivity) getActivity()).setSupportActionBar(mToolbar);

        avater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToastShort("点击了头像");
//                Intent in =new Intent(getApplicationContext(), WelComeActivity.class);
                Intent in =new Intent(getApplicationContext(), LoginBgActivity.class);

                in.putExtra("data","test");

                startActivity(in);
            }
        });
////        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.showToastShort("点击了搜索按钮");
//            }
//        });
    }
    private void initSearchView() {
        //初始化SearchBar

        mSearchView.setVoiceSearch(false);
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);
        mSearchView.setEllipsize(true);
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                TotalStationSearchActivity.launch(getActivity(), query);
//                ToastUtil.showToastShort("开始搜索");
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("query",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        onCreateOptionsMenu(menu,getActivity().getMenuInflater());
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      menu.clear();
       inflater.inflate(R.menu.home_toolbar_menu,menu);
        MenuItem item = menu.findItem(R.id.menu_home_search);
        mSearchView.setMenuItem(item);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        switch (id) {
//            case R.id.id_action_game:
//                //游戏中心
//                startActivity(new Intent(getActivity(), GameCentreActivity.class));
//                break;
//            case R.id.id_action_download:
//                //离线缓存
//                startActivity(new Intent(getActivity(), OffLineDownloadActivity.class));
//                break;
//        }
        return super.onOptionsItemSelected(item);
    }
    public boolean isOpenSearchView() {
        return mSearchView.isSearchOpen();
    }


    public void closeSearchView() {
        mSearchView.closeSearch();
    }
}
