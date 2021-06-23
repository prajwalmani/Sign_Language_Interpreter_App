package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class samplesactivity extends AppCompatActivity {
    ImageView sampleimgview=findViewById(R.id.sampleimgview);
    RelativeLayout relativeLayout;
    samplesactivity.SwipeListner swipeListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplesactivity);
        relativeLayout=findViewById(R.id.relativelayout);
        swipeListner=new SwipeListner(relativeLayout);
        sampleimgview.setImageDrawable(getDrawable(R.drawable.alpsign0));

    }

    private class SwipeListner implements View.OnTouchListener{
        GestureDetector gestureDetector;

        SwipeListner(View view){
            int threshold=100;
            int velocity_thtrshold=100;
            final int[] count = {0};
            ArrayList<Integer> resourceidlist=new ArrayList<>();
            resourceidlist.add(R.drawable.alpsign0);
            resourceidlist.add(R.drawable.alpsign1);
            resourceidlist.add(R.drawable.alpsign2);
            resourceidlist.add(R.drawable.alpsign3);
            resourceidlist.add(R.drawable.alpsign4);
            resourceidlist.add(R.drawable.alpsign5);
            resourceidlist.add(R.drawable.alpsign6);
            resourceidlist.add(R.drawable.alpsign7);
            resourceidlist.add(R.drawable.alpsign8);
            resourceidlist.add(R.drawable.alpsign9);
            resourceidlist.add(R.drawable.alpsign10);
            resourceidlist.add(R.drawable.alpsign11);
            resourceidlist.add(R.drawable.alpsign12);
            resourceidlist.add(R.drawable.alpsign13);
            resourceidlist.add(R.drawable.alpsign14);
            resourceidlist.add(R.drawable.alpsign15);
            resourceidlist.add(R.drawable.alpsign16);
            resourceidlist.add(R.drawable.alpsign17);
            resourceidlist.add(R.drawable.alpsign18);
            resourceidlist.add(R.drawable.alpsign19);
            resourceidlist.add(R.drawable.alpsign20);
            resourceidlist.add(R.drawable.alpsign21);
            resourceidlist.add(R.drawable.alpsign22);
            resourceidlist.add(R.drawable.alpsign23);
            resourceidlist.add(R.drawable.alpsign24);
            resourceidlist.add(R.drawable.alpsign25);
            resourceidlist.add(R.drawable.alpsign26);
            GestureDetector.SimpleOnGestureListener listener=new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xdiff=e2.getX()-e1.getX();
                    float ydiff=e2.getY()-e1.getY();
                    try {
                        if(Math.abs(xdiff)>Math.abs(ydiff)){
                            if(Math.abs(xdiff)>threshold && Math.abs(velocityX)>velocity_thtrshold){
                                if (xdiff>0){
                                    count[0]--;
//                                    swipe right
                                    if(count[0]<0){
                                        count[0]=0;
                                    }
                                    sampleimgview.setImageDrawable(getDrawable(resourceidlist.get(count[0])));

                                }
                                else {
//                                    swipe left
                                    count[0]++;
                                    if(count[0]>26){
                                        count[0]=26;
                                    }
                                    sampleimgview.setImageDrawable(getDrawable(resourceidlist.get(count[0])));
                                }
                                return true;
                            }
                        }
                        else {
                            if(Math.abs(ydiff)>threshold && Math.abs(velocityY)>velocity_thtrshold){
                                if(ydiff>0){
                                    int a=1;
                                }else{
                                    int b=2;
                                }
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            gestureDetector=new GestureDetector(listener);
            view.setOnTouchListener(this);

        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }

}