package com.example.newsapiretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView=findViewById(R.id.webview);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String url=bundle.getString("url");
        webView.loadUrl(url);
    }
}
