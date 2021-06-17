package com.example.sli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class learnactivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    TextView textview;
    SwipeListner swipeListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnactivity);
        relativeLayout=findViewById(R.id.relativelayout);
        textview=findViewById(R.id.textView2);
        swipeListner=new SwipeListner(relativeLayout);
    }

    private class SwipeListner implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListner(View view){
            int threshold=100;
            int velocity_thtrshold=100;


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
                                if(xdiff>0){
                                    textview.setText("Swipe right");
                                }
                                else {
                                    textview.setText("Swipe left");
                                }
                                return true;
                            }
                        }
                        else {
                            if(Math.abs(ydiff)>threshold && Math.abs(velocityY)>velocity_thtrshold){
                                if(ydiff>0){
                                    textview.setText("Swipe Down");
                                }else{
                                    textview.setText("Swipe up");
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