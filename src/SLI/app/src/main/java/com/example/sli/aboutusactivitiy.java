package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class aboutusactivitiy extends AppCompatActivity {

    private ImageView githubrepolink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutusactivitiy);
        githubrepolink=findViewById(R.id.githublogo);
        githubrepolink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengithubrep();
            }
        });
    }

    public void opengithubrep(){
        Intent intent=new Intent(this,githubrepo.class);
        startActivity(intent);
    }
}