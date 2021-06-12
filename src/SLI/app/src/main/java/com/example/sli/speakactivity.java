package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class speakactivity extends AppCompatActivity {
    private WebView webView;
    String giphy_url="file:///android_asset/index.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakactivity);
        webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(giphy_url);
        webView.setWebViewClient(new WebViewClient());

    }
}