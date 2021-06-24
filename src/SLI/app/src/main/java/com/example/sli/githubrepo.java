package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class githubrepo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_githubrepo);
        String github_url="https://github.com/prajwalmani/Sign_Language_Interpreter_App";
        WebView githubwebview;
        githubwebview=findViewById(R.id.githubwebview);
        githubwebview.getSettings().setJavaScriptEnabled(true);
        githubwebview.loadUrl(github_url);

    }
}