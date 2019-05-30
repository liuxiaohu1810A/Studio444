package com.example.studio;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Magnifier;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 下载文件
     */
    private ProgressBar mPb;
    private ImageView mIvTp;
    /**
     * 下载文件
     */
    private Button mBtXiaZai;
    private TextView mTvXiaZai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        //注册EventBus
        EventBus.getDefault().register(this);
    }

    private static final String TAG = "Main2Activity";

    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onMyEventBus(String eventBus) {
//        final int a = eventBus.a;

                mPb.setProgress(Integer.parseInt(eventBus));
                mTvXiaZai.setText("下载进度:" + eventBus + "%");
                Log.d(TAG, "run: " + "下载进度:" + eventBus + "%");
                if (mPb.getProgress()==mPb.getMax()){
                    File file = new File("/sdcard/Pictures/0858c600-1b34-41c1-a2ff-f67cdc376558.png");
                    Glide.with(this).load(file).into(mIvTp);
                }

    }

    private void initView() {

        mPb = (ProgressBar) findViewById(R.id.pb);
        mIvTp = (ImageView) findViewById(R.id.iv_tp);
        mBtXiaZai = (Button) findViewById(R.id.bt_XiaZai);
        mTvXiaZai = (TextView) findViewById(R.id.tv_XiaZai);
        mBtXiaZai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_XiaZai:
                startService(new Intent(Main2Activity.this, Fuwu.class));
                //动态权限
                startData();
                break;
        }
    }

    private void startData() {
          if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
              startService(new Intent(Main2Activity.this, Fuwu.class));
          }else{
              ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
          }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100&&grantResults!=null&&grantResults[0]== PackageManager.PERMISSION_DENIED){
            startService(new Intent(Main2Activity.this, Fuwu.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //解除EventBus
        EventBus.getDefault().unregister(this);
    }
}
