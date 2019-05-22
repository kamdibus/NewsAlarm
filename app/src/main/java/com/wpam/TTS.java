package com.wpam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;


public class TTS extends Activity implements TextToSpeech.OnInitListener,OnUtteranceCompletedListener {

    private TextToSpeech tts = null;
    private String title = "";
    private String content = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startingIntent = this.getIntent();
        title = startingIntent.getStringExtra("title");
        content = startingIntent.getStringExtra("content");
        tts = new TextToSpeech(this,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts!=null) {
            tts.shutdown();
        }
    }

    public void onInit(int status) {
        tts.speak(title + content, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        tts.shutdown();
        tts = null;
        finish();
    }
}