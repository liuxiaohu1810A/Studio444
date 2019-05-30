package com.example.studio;
//刘小虎1810A

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.studio.fragments.FragmentA;
import com.example.studio.fragments.FragmentB;

public class MainActivity extends AppCompatActivity {

    /**
     * Hello World!
     */
    private TextView mWqe;
    private RecyclerView mRlv;
    private ViewPager mVp;
    private TabLayout mTab;
    /**
     * asd
     */
    private TextView mTvName;
    private Toolbar mTool;
    private FragmentManager fm;
    private FrameLayout mFl;
    private FragmentA fa;
    private FragmentB fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTool();
        initFrag();
        initData();
    }

    private void initTool() {
        mTool.setTitle("");
        mTool.setTitleTextColor(Color.GREEN);
        setSupportActionBar(mTool);
    }

    private void initFrag() {
        fa = new FragmentA();
        fb = new FragmentB();
        fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.add(R.id.fl,fa);
        tr.add(R.id.fl,fb);
        tr.show(fa);
        tr.hide(fb);
        tr.commit();
    }

    private void initData() {
        mTab.addTab(mTab.newTab().setIcon(R.drawable.tab).setText("首页"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.tab2).setText("我的"));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction tran = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        tran.show(fa);
                        tran.hide(fb);
                        tran.commit();
                        break;
                    case 1:
                        tran.show(fb);
                        tran.hide(fa);
                        tran.commit();
                        break;
                }
                mTool.setTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {

        mTab = (TabLayout) findViewById(R.id.tab);
        mTool = (Toolbar) findViewById(R.id.tool);
        mFl = (FrameLayout) findViewById(R.id.fl);
    }
}
