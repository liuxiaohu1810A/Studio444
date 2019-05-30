package com.example.day02.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.day02.R;
import com.example.day02.XiazaiActivity;

public class FragmentTwo extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * 我的收藏
     */
    private TextView mTvLove;
    /**
     * 我的下载
     */
    private TextView mTvXiaZai;
    /**
     * 积分商城
     */
    private TextView mTvJiFeng;
    /**
     * 会员状态
     */
    private TextView mTvVip;
    /**
     * 意见反馈
     */
    private TextView mTvYiJian;
    /**
     * 联系我们
     */
    private TextView mTvPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvLove = (TextView) view.findViewById(R.id.tv_love);
        mTvXiaZai = (TextView) view.findViewById(R.id.tv_XiaZai);
        mTvJiFeng = (TextView) view.findViewById(R.id.tv_JiFeng);
        mTvVip = (TextView) view.findViewById(R.id.tv_Vip);
        mTvYiJian = (TextView) view.findViewById(R.id.tv_YiJian);
        mTvPhone = (TextView) view.findViewById(R.id.tv_Phone);
        mTvLove.setOnClickListener(this);
        mTvXiaZai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_love:
                Intent in = new Intent();
                startActivity(in);
                break;
            case R.id.tv_XiaZai:
                startActivity(new Intent(getContext(), XiazaiActivity.class));
                break;
        }
    }
}
