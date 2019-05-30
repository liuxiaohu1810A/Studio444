package com.example.studio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.Multipart;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 上传文件
     */
    private Button mBtShangChuang;
    /**
     * 上传
     */
    private TextView mTvShangChuang;
    private ImageView mIvTp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
    }

    private void initView() {
        mBtShangChuang = (Button) findViewById(R.id.bt_ShangChuang);
        mBtShangChuang.setOnClickListener(this);
        mTvShangChuang = (TextView) findViewById(R.id.tv_ShangChuang);
        mIvTp = (ImageView) findViewById(R.id.iv_tp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_ShangChuang:
                initData();
                break;
        }
    }

    private static final String TAG = "MyActivity";
    private void initData() {
        File file = new File("/sdcard/Pictures/0858c600-1b34-41c1-a2ff-f67cdc376558.png");
        if (file.exists()){
            Log.d(TAG, "initData: 已存在");
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("key", "1810A")
                .addFormDataPart("file", file.getName(),requestBody )
                .setType(MultipartBody.FORM)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(multipartBody)
                .url("http://yun918.cn/study/public/file_upload.php")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvShangChuang.setText(result);
                        Log.d(TAG, "run: "+result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject data = jsonObject.getJSONObject("data");
                            String url = data.optString("url");
                            Glide.with(MyActivity.this).load(url).into(mIvTp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
