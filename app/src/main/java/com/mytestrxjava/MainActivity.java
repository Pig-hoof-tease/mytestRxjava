package com.mytestrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mytestrxjava.BaseRecylerView.CommonAdapter;
import com.mytestrxjava.BaseRecylerView.MultiItemCommonAdapter;
import com.mytestrxjava.BaseRecylerView.MultiItemTypeSupport;
import com.mytestrxjava.BaseRecylerView.RCViewHolder;
import com.mytestrxjava.View.CustomTitleView;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private RecyclerView homeRecyclerView;
    private CommonAdapter mhhomeAdapter;
    private MultiItemCommonAdapter multiItemCommonAdapter;
    private CustomTitleView textView;
    private List<String> mData = new ArrayList<>();
    private boolean isInsert = false;
    private MultiItemTypeSupport ItemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        for (int i = 0; i < 100; i++) {
            mData.add("" + i);
        }
        homeRecyclerView = (RecyclerView) findViewById(R.id.homeRecyclerView);
        textView = (CustomTitleView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInsert) {
                    mhhomeAdapter.removeData(1);
                    isInsert = false;
                } else {
                    mhhomeAdapter.addData(1, "fdfds");
                    isInsert = true;
                }
            }
        });
        homeRecyclerView.addItemDecoration(new DividerGridItemDecoration(getApplicationContext()));
//        homeRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL_LIST));
//        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        homeRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));

        homeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
//        homeRecyclerView.setAdapter(mhhomeAdapter= new CommonAdapter(getApplicationContext(),R.layout.home_item,mData) {
//            @Override
//            public void convert(RCViewHolder holder, Object o) {
//             TextView tv=holder.getView(R.id.tv_home_item);
//                tv.setText("11111111111");
//            }
//        });
        ItemType = new MultiItemTypeSupport() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 1) {
                    return R.layout.home_item;
                } else {
                    return R.layout.home_item2;
                }


            }

            @Override
            public int getItemViewType(int postion, Object o) {
                return postion % 2;
            }
        };
        homeRecyclerView.setAdapter(multiItemCommonAdapter = new MultiItemCommonAdapter(getApplicationContext(), mData, ItemType) {
            @Override
            public void convert(RCViewHolder holder, Object o) {
                TextView tv = holder.getView(R.id.tv_home_item);
                tv.setText((String) o);
            }
        });


    }


    private void shella() {
        String com = "ifconfig eth0 192.168.2.210 netmask 255.255.255.0";

        try {
            Process suProcess = Runtime.getRuntime().exec("su");//root权限
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
            // Execute commands that require root access
            os.writeBytes(com + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            Log.d(TAG, "ip设置成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
