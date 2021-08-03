package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class samplesActivity extends AppCompatActivity {
    Button prevbtn;
    Button nextbtn;
    ImageView sampleimgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        prevbtn=findViewById(R.id.prevbtn);
        nextbtn=findViewById(R.id.nxtbtn);
        sampleimgview=findViewById(R.id.sampleimgview);
        final int[] count = {0};
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0]++;
                sampleimgview.setImageDrawable(getDrawable(R.drawable.q));

            }
        });
        prevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0]--;
                sampleimgview.setImageDrawable(getDrawable(R.drawable.r));

            }
        });

        sampleimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imageviewresid=sampleimgview.getId();
                Intent intent=new Intent(samplesActivity.this,samplepredictActivity.class);
                intent.putExtra("imageviewresid",imageviewresid);
                startActivity(intent);

            }
        });
    }
}