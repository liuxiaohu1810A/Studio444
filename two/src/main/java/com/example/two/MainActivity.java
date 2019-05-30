package com.example.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import org.greenrobot.greendao.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRlv;
    private ArrayList<User> users;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        List<User> select = DBUtils.select();
        users.addAll(select);
        adapter.notifyDataSetChanged();

    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        //创建适配器
        users = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            User user = new User(null, "张三"+i, 18);
            DBUtils.insert(user);
        }
        adapter = new MyAdapter(this, users);
        mRlv.setAdapter(adapter);
    }
}
