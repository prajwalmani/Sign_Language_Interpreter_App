package com.example.sli;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.KeyEvent;
import android.view.View;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class speakactivity extends AppCompatActivity implements SpellCheckerSession.SpellCheckerSessionListener {
    WebView webView;
    EditText speechtext;
    SpellCheckerSession mscs;
    ArrayList<String> buff_data=new ArrayList<>();
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakactivity);
        String giphy_url="file:///android_asset/index.html";
        ImageView mic=findViewById(R.id.micimgview);
        speechtext=findViewById(R.id.speechedittext);
        SpeechRecognizer speechRecognizer;
        webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(giphy_url);
        webView.setWebViewClient(new WebViewClient());
        buff_data.add("Hello");
        load_gif(buff_data);
        speechtext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_ENTER){
                    buff_data.remove(0);
                    buff_data.add(speechtext.getText().toString());
                    mscs.getSentenceSuggestions(new TextInfo[]{new TextInfo(speechtext.getText().toString())}, 1);
                    load_gif(buff_data);
                    speechtext.setText(buff_data.get(0));
                    return true;
                }
                return false;
            }
        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.RECORD_AUDIO
            },1);
        }
        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
        Intent speechrecognizerintent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        final int[] count = {0};
        mic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count[0] ==0){
                    mic.setImageDrawable(getDrawable(R.drawable.redmic));
                    speechRecognizer.startListening(speechrecognizerintent);
                    count[0] =1;
                }
                else{
                    mic.setImageDrawable(getDrawable(R.drawable.micon));
                    speechRecognizer.stopListening();
                    count[0] =0;
                }
            }
        });
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {


            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data =results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                speechtext.setText(data.get(0));
                load_gif(data);
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT);
            }
            else{
//                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT);
            }

        }
    }

    public void load_gif(ArrayList<String> data){
        String url="file:///android_asset/index.html";
        String searchterm= data.get(0);
        speechtext.setText(searchterm);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                webView.loadUrl("javascript:giphy('" + searchterm + "')");

            }
        });

    }

    public void onResume() {
        super.onResume();
        final TextServicesManager tsm=(TextServicesManager) getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
        mscs=tsm.newSpellCheckerSession(null,null,this,true);

    }

    public void onPause(){
        super.onPause();
        if(mscs!=null){
            mscs.close();
        }
    }


//        public void getSentenceSuggestions(final SuggestionsInfo[] arg0){}

    @Override
    public void onGetSuggestions(final SuggestionsInfo[] arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] arg0) {
        final StringBuilder sb = new StringBuilder();
        for(int i=0;i<arg0.length;++i){
            final int len=arg0[i].getSuggestionsCount();
            sb.append('\n');
            for(int j=0;j<len;++j){
                sb.append(","+arg0[i].getSuggestionsInfoAt(j));
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buff_data.remove(0);
                buff_data.add(sb.toString());
            }
        });
    }
}