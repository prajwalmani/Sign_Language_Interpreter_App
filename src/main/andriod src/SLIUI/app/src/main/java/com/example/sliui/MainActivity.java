package com.example.sliui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button predictbtn=(Button)findViewById(R.id.predictbtn);
        predictbtn.setOnClickListener(this);
        Button speakbtn=(Button)findViewById(R.id.speakbtn);
        speakbtn.setOnClickListener(this);
        Button learnbtn=(Button)findViewById(R.id.learnbtn);
        learnbtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.predictbtn:
                break;
            case R.id.speakbtn:
                break;
            case R.id.learnbtn:
                break;
            default:
                break;
        }

    }
}