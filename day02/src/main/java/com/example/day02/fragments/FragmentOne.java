package com.example.day02.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.day02.DBUtils;
import com.example.day02.R;

import com.example.day02.WebActivity;
import com.example.day02.adapters.MyAdapter_one;
import com.example.day02.bean.DatasBean;
import com.example.day02.bean.User;
import com.example.day02.presenter.Presenter;
import com.example.day02.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment implements IView {
    private View view;
    private XRecyclerView mXRlv;
    private ArrayList<User.DataBean> listban;
    private ArrayList<DatasBean> beans;
    private MyAdapter_one adapterOne;
    private int mpage=0;
    private BroadClass broadClass;
    private BroadClass2 broadClass2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Presenter presenter = new Presenter(this);
        presenter.onDataP();
    }

    private void initView(View view) {
        mXRlv = (XRecyclerView) view.findViewById(R.id.xRlv);
        //添加布局管理器
        mXRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        //分割线
        mXRlv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        //创建适配器
        listban = new ArrayList<>();
        beans = new ArrayList<>();
        adapterOne = new MyAdapter_one(getContext(),listban,beans);
        mXRlv.setAdapter(adapterOne);
        //自动加载
        mXRlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                beans.clear();
              mpage=1;
              initData();
              mXRlv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mpage++;
                initData();
                mXRlv.loadMoreComplete();
            }
        });
    }
//接收MVP数据
    private static final String TAG = "FragmentOne";
    @Override
    public void onSuccess(List<DatasBean> success) {
         beans.addAll(success);
        adapterOne.notifyDataSetChanged();
    }

    @Override
    public void onSuccessBan(List<User.DataBean> successban) {
        listban.addAll(successban);
        adapterOne.notifyDataSetChanged();
    }

    @Override
    public void onPage(int page) {
        mpage=page;
    }

    @Override
    public void onError(String error) {
        Log.d(TAG, "onError: "+error);
    }
    //自定义广播内部类
    class BroadClass extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int data = intent.getIntExtra("data", 0);
            DatasBean bean = beans.get(data);
            DBUtils.insert(bean);
            Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
        }
    }


    class BroadClass2 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int data = intent.getIntExtra("data", 0);
            DatasBean bean = beans.get(data);
            Intent in = new Intent(getContext(), WebActivity.class);
            in.putExtra("link",bean.getLink());
            startActivity(in);
        }
    }

    //注册广播
    @Override
    public void onResume() {
        super.onResume();
        broadClass = new BroadClass();
        IntentFilter ii = new IntentFilter("ii");
        getActivity().registerReceiver(broadClass,ii);


        broadClass2 = new BroadClass2();
        IntentFilter pp = new IntentFilter("pp");
        getActivity().registerReceiver(broadClass2,pp);
    }
    //解除广播
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadClass);

        getActivity().unregisterReceiver(broadClass2);
    }
}
