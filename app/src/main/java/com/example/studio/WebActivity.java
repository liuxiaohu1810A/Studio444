package com.example.studio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
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
        String da = in.getStringExtra("da");
        mWeb.loadUrl(da);
        mWeb.setWebViewClient(new WebViewClient());
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
    }
}
