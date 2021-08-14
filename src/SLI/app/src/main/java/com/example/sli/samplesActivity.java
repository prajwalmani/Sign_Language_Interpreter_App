package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class samplesactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplesactivity);
        String github_url="https://github.com/prajwalmani/Sign_Language_Interpreter_App/tree/master/samples";
        WebView samplewebview;
        samplewebview=findViewById(R.id.samplewebview);
        samplewebview.getSettings().setJavaScriptEnabled(true);
        samplewebview.loadUrl(github_url);
    }
}