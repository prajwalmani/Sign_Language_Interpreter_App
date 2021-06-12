package com.example.sli;
import com.example.sli.ml.Model;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;

import java.lang.AutoCloseable;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import android.speech.tts.TextToSpeech;


public class predictactivity extends AppCompatActivity {
    Button camerabtn;
    ImageView imageView;
    TextView textView;
    TextToSpeech textToSpeech;

//    public interface NDArray
//            extends java.lang.AutoCloseable{
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictactivity);
        camerabtn=(Button)findViewById(R.id.camerabtn);
        imageView=(ImageView)findViewById(R.id.imageView);
        textView=findViewById(R.id.txtview);
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

// Text to Speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }
//    public static int argmax(float[] args) {
//        int result;
//        result = 0;
//        for (int i = 1; i < args.length; i++) {
//            if (Float.compare(args[result], args[i]) < 0)
//                result = i;
//        }
//        return result;
//    }

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
                String varibletype = outputFeature0.getClass().getSimpleName();
//                textView.setText();

//                int labelvalue=argmax(outputFeature0.getFloatArray());
//                NDArray ndArray= (NDArray) outputFeature0;

//                textView.setText(outputFeature0.getFloatArray()[0]+"\n"+outputFeature0.getFloatArray()[1]+"\n"+outputFeature0.getFloatArray()[2]+"\n"+outputFeature0.getFloatArray()[3]+"\n"+outputFeature0.getFloatArray()[4]);
//                textView.setText(Integer.toString(labelvalue));
//                textToSpeech.speak(Integer.toString(labelvalue),TextToSpeech.QUEUE_FLUSH,null);

                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }


        }
    }
}