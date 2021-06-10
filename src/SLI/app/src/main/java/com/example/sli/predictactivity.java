package com.example.sli;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.support.model.Model;



public class predictactivity extends AppCompatActivity {
    Button camerabtn;
    ImageView imageView;
    static final int CAM_REQUEST=1;
    private int mInputSize=224;
    private String mModelPath="model.tflite";
    private String mLabelPath="label.txt";
    private Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictactivity);
        camerabtn=(Button)findViewById(R.id.camerabtn);
        imageView=(ImageView)findViewById(R.id.imageView);
        if (ContextCompat.checkSelfPermission(predictactivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(predictactivity.this,new String[]{
                    Manifest.permission.CAMERA},
                    100);

        }
        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bundle bundle=data.getExtras();
            Bitmap captureimage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(captureimage);
//            ImageProcessor imageProcessor =
//                    new ImageProcessor.Builder()
//                            .add(new ResizeOp(64, 64, ResizeOp.ResizeMethod.BILINEAR))
//                            .build();
//            TensorImage tensorImage = new TensorImage(DataType.UINT8);
//            tensorImage.load(captureimage);
//            tensorImage = imageProcessor.process(tensorImage);
//            TensorBuffer probabilityBuffer =
//                    TensorBuffer.createFixedSize(new int[]{1, 1001}, DataType.UINT8);
//            try{
//                MappedByteBuffer tfliteModel
//                        = FileUtil.loadMappedFile(activity,
//                        "model.tflite");
//                Interpreter tflite = new Interpreter(tfliteModel);
//            } catch (IOException e){
//                Log.e("tfliteSupport", "Error reading model", e);
//            }
//
//// Running inference
//            if(null != tflite) {
//                tflite.run(tImage.getBuffer(), probabilityBuffer.getBuffer());
//            }


        }
    }
}