package com.example.sli;
import com.example.sli.ml.Model;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.flatbuffers.ByteBufferUtil;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.List;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
//import org.tensorflow.lite.support.model.Model;




public class predictactivity extends AppCompatActivity {
    Button camerabtn;
    ImageView imageView;
    static final int CAM_REQUEST=1;
    TextView textView;

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
            Bitmap img =Bitmap.createScaledBitmap(captureimage,64,64,true);
            try {
                Model model = Model.newInstance(getApplicationContext());

                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 64, 64, 3}, DataType.FLOAT32);
                TensorImage tensorImage= new TensorImage(DataType.FLOAT32);
                tensorImage.load(img);
                ByteBuffer byteBuffer=tensorImage.getBuffer();
                inputFeature0.loadBuffer(byteBuffer);

                // Runs model inference and gets result.
                Model.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                textView.setText(outputFeature0.getFloatArray()[0]+"\n"+outputFeature0.getFloatArray()[1]);


                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }


        }
    }
}