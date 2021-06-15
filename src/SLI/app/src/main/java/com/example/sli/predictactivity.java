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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;


import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import android.speech.tts.TextToSpeech;


public class predictactivity extends AppCompatActivity {
    Button camerabtn;
    ImageView imageView;
    TextView textView;
    TextToSpeech textToSpeech;

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
    public static int argmax(float[] args) {
        int result;
        result = 0;
        for (int i = 1; i < args.length; i++) {
            if (Float.compare(args[result], args[i]) < 0)
                result = i;
        }
        return result;
    }

    public String readlabelfile(int pos){
        String label;
        File labelfile=new File("assets/","labels.txt");
        ArrayList<String> labellist=new ArrayList<String>();
        try{
            FileInputStream fin=new FileInputStream(labelfile);
            DataInputStream din=new DataInputStream(fin);
            BufferedReader bin=new BufferedReader(new InputStreamReader(din));
            String strline;
            while ((strline=bin.readLine())!=null){
                ArrayList<String> buff=new ArrayList<String>();
                buff.add(strline);
                labellist.addAll(buff);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return labellist.get(pos);
    }


    public String labelcond(int pos){
//        I was jobless and bored so took this approch
        if(pos==0)
            return "A";
        else if(pos==1)
            return "Airplane";
        else if(pos==2)
            return "B";
        else if(pos==3)
            return "Bathroom";
        else if(pos==4)
            return "Bye";
        else if(pos==5)
            return "C";
        else if(pos==6)
            return "D";
        else if(pos==7)
            return "E";
        else if(pos==8)
            return "F";
        else if(pos==9)
            return "G";
        else if(pos==10)
            return "H";
        else if(pos==11)
            return "Hello";
        else if(pos==12)
            return "Help";
        else if(pos==13)
            return "Hurt";
        else if(pos==14)
            return "I";
        else if(pos==15)
            return "I Love You";
        else if(pos==16)
            return "J";
        else if(pos==17)
            return "K";
        else if(pos==18)
            return "L";
        else if(pos==19)
            return "M";
        else if(pos==20)
            return "More";
        else  if(pos==21)
            return "N";
        else  if(pos==22)
            return "No";
        else  if(pos==23)
            return "O";
        else  if(pos==24)
            return "P";
        else  if(pos==25)
            return "Play";
        else  if(pos==26)
            return "Q";
        else if(pos==27)
            return "R";
        else  if(pos==28)
            return "S";
        else if(pos==29)
            return "Something";
        else if(pos==30)
            return "Sorry";
        else if(pos==31)
            return "T";
        else if(pos==32)
            return "Thank You";
        else if(pos==33)
            return "U";
        else if(pos==34)
            return "V";
        else if(pos==35)
            return "W";
        else if(pos==36)
            return "X";
        else if(pos==37)
            return "Y";
        else if(pos==38)
            return "Yes";
        else if(pos==39)
            return "Z";
        else
            return "Try Again!";
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
                int labelpos=argmax(outputFeature0.getFloatArray());
                String label=labelcond(labelpos);
                textView.setText(label);
                textToSpeech.speak(label,TextToSpeech.QUEUE_FLUSH,null);

                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }
        }
    }
}