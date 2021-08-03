package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class samplepredictActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplepredict);
        Bundle bundle=getIntent().getExtras();
        imageView=findViewById(R.id.imageview);
        if(bundle!=null){
            int imageviewresvalue=bundle.getInt("imageviewresid");
            imageView.setImageDrawable(R.);

        }
    }
}