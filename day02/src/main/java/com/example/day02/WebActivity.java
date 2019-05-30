package com.example.day02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        Intent in = getIntent();
        String link = in.getStringExtra("link");
        mWeb.loadUrl(link);
        mWeb.setWebViewClient(new WebViewClient());
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
    }
}
