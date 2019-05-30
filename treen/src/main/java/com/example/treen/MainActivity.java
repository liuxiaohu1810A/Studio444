package com.example.treen;
//刘小虎1810A

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        //创建适配器
        new ArrayList<>();
    }
}
