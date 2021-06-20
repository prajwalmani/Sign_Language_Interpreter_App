package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button predictbtn;
    private Button speakbtn;
    private Button learnbtn;
    private Button aboutusbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        predictbtn=(Button)findViewById(R.id.predictbtn);
        predictbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpredictactiv();
            }

        });

        speakbtn=(Button)findViewById(R.id.speakbtn);
        speakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openspeakactiv();
            }

        });

        learnbtn=findViewById(R.id.learnbtn);
        learnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlearnactiv();
            }
        });

        aboutusbtn=findViewById(R.id.aboutusbtn);
        aboutusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensboutusactiv();
            }
        });

    }

    public void openpredictactiv(){
        Intent intent =new Intent(this,predictactivity.class);
        startActivity(intent);

    }

    public void openspeakactiv(){
        Intent intent =new Intent(this,speakactivity.class);
        startActivity(intent);

    }

    public void openlearnactiv(){
        Intent intent=new Intent(this,learnactivity.class);
        startActivity(intent);
    }

    public void opensboutusactiv(){
        Intent intent=new Intent(this,aboutusactivitiy.class);
        startActivity(intent);
    }
}
