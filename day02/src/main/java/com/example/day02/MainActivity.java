package com.example.day02;
//刘小虎1810A

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.day02.fragments.FragmentOne;
import com.example.day02.fragments.FragmentTwo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TabLayout mTab;


    private Toolbar mTool;
    private ViewPager mVp;
    private ArrayList<Fragment> fm;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTool();
        initVape();
        initTab();
       /* fa = new FragmentOne();
        fb = new FragmentTwo();
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl, fa);
        ft.add(R.id.fl, fb);
        ft.show(fa);
        ft.hide(fb);
        ft.commit();
        initTab();*/
    }

    private void initVape() {
        fm = new ArrayList<>();
        fm.add(new FragmentOne());
        fm.add(new FragmentTwo());
        vpAdapter = new VpAdapter(getSupportFragmentManager(),fm);
        mVp.setAdapter(vpAdapter);

    }

    private void initTool() {
        setTitle("首页");
        setSupportActionBar(mTool);
    }

    private void initTab() {
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setIcon(R.drawable.tab).setText("首页");
        mTab.getTabAt(1).setIcon(R.drawable.tab2).setText("我的");
       /* mTab.addTab(mTab.newTab().setIcon(R.drawable.tab).setText("首页"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.tab2).setText("我的"));*/
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               /* FragmentTransaction tran = fm.beginTransaction();
                switch (tab.getPosition()) {
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
                }*/
               mVp.setCurrentItem(tab.getPosition());
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
        /*mFl = (FrameLayout) findViewById(R.id.fl);*/
        mTab = (TabLayout) findViewById(R.id.tab);
        mTool = (Toolbar) findViewById(R.id.tool);
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
