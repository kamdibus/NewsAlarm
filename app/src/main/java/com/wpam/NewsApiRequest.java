package com.wpam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class NewsApiRequest {

    private String query2;
    private String url = "https://newsapi.org/v2/";
    private String apiKey;
    private RequestQueue queue;
    private StringRequest stringRequest;


    public NewsApiRequest(Context context) {
        queue = Volley.newRequestQueue(context);

        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open("keyfile")));
            apiKey = br.readLine();
        } catch (IOException e) {
            Toast.makeText(context,"Error reading apiKey",Toast.LENGTH_LONG).show();
        }

        if(settings.getBoolean("topHeadlines", false)) {
            query2 = url + "top-headlines?country=us&apiKey=" + apiKey;
        } else if(!settings.getString("country", "country").equals("country")) {
            SupportedCountries supportedCountries = new SupportedCountries();
            query2 = url + "top-headlines?country=" + supportedCountries.getCode(settings.getString("country", "")) + "&apiKey=" + apiKey;
        } else if(!settings.getString("category", "category").equals("category")) {
            query2 = url + "top-headlines?country=us&category=" + settings.getString("category", "") + "&apiKey=" + apiKey;
        } else if(!settings.getString("source", "source").equals("source")) {
            SupportedSources supportedSources = new SupportedSources();
            query2 = url + "top-headlines?sources=" + supportedSources.getCode(settings.getString("source", "")) + "&apiKey=" + apiKey;
        } else if(!settings.getString("phrase", "undef").equals("undef")) {
            query2 = url + "top-headlines?q=" + settings.getString("phrase", "") + "&apiKey=" + apiKey;
        } else {
            query2 = url + "top-headlines?country=us&apiKey=" + apiKey;
        }

        stringRequest = new StringRequest(Request.Method.GET, query2,
                response -> {
                    String title = "Didn't parse";
                    String content = "Content";
                    String author = "Author";
                    String description = "Description";
                    String time = "PublishedAt";
                    String source = "Source";
                    String url = "url";
                    String urlToImage = "urlToImage";
                    try {
                        JSONObject reader = new JSONObject(response);
                        JSONArray articles = reader.getJSONArray("articles");
                        reader = articles.getJSONObject(0);
                        title = reader.getString("title");
                        content = reader.getString("content");
                        author = reader.getString("author");
                        description = reader.getString("description");
                        time = reader.getString("publishedAt");
                        source = reader.getJSONObject("source").getString("name");
                        url = reader.getString("url");
                        urlToImage = reader.getString("urlToImage");
                    } catch (JSONException e) {
                        Toast.makeText(context, "query " + query2, Toast.LENGTH_LONG).show();
                    }

                    content = content.replaceAll("\\[.*\\]", "");

                    Intent intent = new Intent(context, TTS.class);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    intent.putExtra("author", author);
                    intent.putExtra("description", description);
                    intent.putExtra("time", time);
                    intent.putExtra("source", source);
                    intent.putExtra("url", url);
                    intent.putExtra("urlToImage", urlToImage);

                    context.startActivity(intent);
                }, error -> Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show());

    }

    public void query() {
        queue.add(stringRequest);

    }
}
