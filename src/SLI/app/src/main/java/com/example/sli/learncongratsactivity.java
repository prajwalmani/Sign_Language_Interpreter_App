package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class learncongratsactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learncongratsactivity);
        ImageView congratsimageview;
        congratsimageview = findViewById(R.id.congrarsimgview);
        congratsimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpredactiv();

            }
        });
    }

    private void openpredactiv() {
        Intent intent =new Intent(this,predictactivity.class);
        startActivity(intent);
    }

}