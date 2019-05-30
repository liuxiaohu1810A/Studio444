package com.example.day02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class XiazaiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvXiaZai;
    private ImageView mIvTt;
    /**
     * 下载
     */
    private Button mBtXiaZai;
    private ProgressBar mPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiazai);
        initView();
    }

    private void initView() {
        mTvXiaZai = (TextView) findViewById(R.id.tv_XiaZai);
        mIvTt = (ImageView) findViewById(R.id.iv_tt);
        mBtXiaZai = (Button) findViewById(R.id.bt_XiaZai);
        mBtXiaZai.setOnClickListener(this);
        mPb = (ProgressBar) findViewById(R.id.pb);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_XiaZai:
                initData();
                if (mPb.getProgress()==mPb.getMax()){
                    File file = new File("/sdcard/Pictures/0858c600-1b34-41c1-a2ff-f67cdc376558.png");
                    Glide.with(this).load(file).into(mIvTt);
                }
                break;
        }
    }

    private static final String TAG = "XiazaiActivity";
    private void initData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = new File("/sdcard/Pictures/0858c600-1b34-41c1-a2ff-f67cdc376558.png");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    HttpURLConnection con = (HttpURLConnection) new URL("https://www.wanandroid.com/blogimgs/0858c600-1b34-41c1-a2ff-f67cdc376558.png").openConnection();
                    final int length = con.getContentLength();
                    if (length <= 0) {
                        return;
                    }
                    InputStream in = con.getInputStream();
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] by = new byte[1024 * 4];
                    int len = 0;
                    int str = 0;
                    while ((len = in.read(by)) != -1) {
                        out.write(by, 0, len);
                        str += len;
                        mPb.setProgress(str * 100 / length);
                        final int finalStr = str;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvXiaZai.setText("下载进度:"+ finalStr * 100 / length+"%");
                                Log.d(TAG, "run: "+"下载进度:"+ finalStr * 100 / length+"%");
                            }
                        });


                    }
                    con.disconnect();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
