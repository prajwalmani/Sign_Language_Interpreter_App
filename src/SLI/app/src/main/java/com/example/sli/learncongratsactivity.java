package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class learncongratsactivity extends AppCompatActivity {
    ImageView predictimgview;
    ImageView speakimageview;
    Button predictlinkbtn;
    Button speaklinkbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learncongratsactivity);

        predictlinkbtn = findViewById(R.id.predictlinkbtn);
        speaklinkbtn=findViewById(R.id.sppeaklinkbtn);
        predictlinkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpredactiv();

            }
        });

        speaklinkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openspeakactiv();

            }
        });


    }

    private void openpredactiv() {
        Intent intent =new Intent(this,predictactivity.class);
        startActivity(intent);
    }

    private void openspeakactiv() {
        Intent intent =new Intent(this,speakactivity.class);
        startActivity(intent);
    }

}