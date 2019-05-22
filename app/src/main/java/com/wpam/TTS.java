package com.wpam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


public class TTS extends Activity implements TextToSpeech.OnInitListener,OnUtteranceCompletedListener {

    private TextToSpeech tts = null;
    private String title = "";
    private String content = "";
    private String desc = "";
    TextView titleV;
    TextView source;
    TextView date;
    TextView author;
    TextView description;
    TextView contentV;
    TextView url;
    ImageView image;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        Intent intent = this.getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        String author = intent.getStringExtra("author");
        String description = intent.getStringExtra("description");
        desc = description;
        String time = intent.getStringExtra("time");
        String source = intent.getStringExtra("source");
        String url = intent.getStringExtra("url");
        String urlToImage = intent.getStringExtra("urlToImage");

        this.source = findViewById(R.id.source);
        this.titleV = findViewById(R.id.title);
        this.date = findViewById(R.id.date);
        this.author = findViewById(R.id.author);
        this.description = findViewById(R.id.description);
        this.contentV = findViewById(R.id.content);
        this.url = findViewById(R.id.url);
        titleV.setText(title);
        this.source.setText(source);
        this.date.setText(time);
        this.author.setText(author);
        this.description.setText(description);
        this.contentV.setText(content);
        this.url.setText(url);
        this.url.setMovementMethod(LinkMovementMethod.getInstance());
        new DownloadImageTask(findViewById(R.id.image))
                .execute(urlToImage);
        this.tts = new TextToSpeech(this, this);
        tts.setPitch(0.8F);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }

    public void onInit(int status) {
        String close = "Find out more at link below.";
        tts.speak(title + "\n" + desc + "." + content + close, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        tts.shutdown();
        tts = null;
        finish();
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}