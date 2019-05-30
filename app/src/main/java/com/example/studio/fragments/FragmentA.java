package com.example.studio.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.studio.Main2Activity;
import com.example.studio.MyActivity;
import com.example.studio.R;
import com.example.studio.User;
import com.example.studio.WebActivity;
import com.example.studio.presenter.Presenter;
import com.example.studio.view.IView;

import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment implements IView {
    private View view;
    private RecyclerView mRlv;
    private ArrayList<User.DataBean.DatasBean> beans;
    private MyAdapter myAdapter;
    private Presenter presenter;
    private BroadClass broadClass;
    private BroadClass2 broadClass2;
    private BroadClass3 broadClass3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmnetone, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        presenter = new Presenter(this);
        presenter.getDataP();
    }

    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        //创建适配器
        beans = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), beans);
        mRlv.setAdapter(myAdapter);
    }

    private static final String TAG = "FragmentA";
    @Override
    public void onSuccess(List<User.DataBean.DatasBean> success) {
         beans.addAll(success);
         myAdapter.notifyDataSetChanged();
        Log.d(TAG, "onSuccess: "+success);
    }

    @Override
    public void onError(String error) {
        Log.d(TAG, "onError: "+error);
    }
    public class BroadClass extends BroadcastReceiver{

        private Notification build;
        private int i=1;

        @Override
        public void onReceive(Context context, Intent intent) {
            int data = intent.getIntExtra("data", 0);
            User.DataBean.DatasBean bean = beans.get(data);
            NotificationManager service = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            Intent in = new Intent(context, WebActivity.class);
            in.putExtra("da",bean.getLink());
            PendingIntent activities = PendingIntent.getActivity(context, 10,in,PendingIntent.FLAG_CANCEL_CURRENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("10", "ss", NotificationManager.IMPORTANCE_DEFAULT);
                service.createNotificationChannel(channel);
            }
            build = new NotificationCompat.Builder(getContext(), "10")
                    .setAutoCancel(true)
                    .setContentTitle("通知")
                    .setContentText("进入详情")
                    .setContentIntent(activities)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .build();
             i++;
             service.notify(i,build);
        }
    }
    public class BroadClass2 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int data = intent.getIntExtra("data", 0);
            startActivity(new Intent(context, Main2Activity.class));
        }
    }
    public class BroadClass3 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int data = intent.getIntExtra("da", 0);
            startActivity(new Intent(context,MyActivity.class));
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        broadClass = new BroadClass();
        IntentFilter uuu = new IntentFilter("uuu");
        getActivity().registerReceiver(broadClass,uuu);

        broadClass2 = new BroadClass2();
        IntentFilter iii = new IntentFilter("iii");
        getActivity().registerReceiver(broadClass2,iii);

        broadClass3 = new BroadClass3();
        IntentFilter jjj = new IntentFilter("jjj");
        getActivity().registerReceiver(broadClass3,jjj);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadClass);

        getActivity().unregisterReceiver(broadClass2);

        getActivity().unregisterReceiver(broadClass3);
    }
}
