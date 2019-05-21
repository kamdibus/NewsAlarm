package com.wpam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class NewsApiRequest {

    private String query;
    private String url = "https://newsapi.org/v2/";
    private String urlTest = "https://newsapi.org/v2/top-headlines?country=us&apiKey=20377f1b221e4fcabe623771a9219cd4";
    private String apiKey;
    private RequestQueue queue;
    private StringRequest stringRequest;


    public NewsApiRequest(final Context context) {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open("keyfile")));
            apiKey = br.readLine();
        } catch (IOException e) {
            Toast.makeText(context,"Error reading apiKey",Toast.LENGTH_LONG).show();
        }

        if(settings.getBoolean("topHeadlines", false)) {
            query = url + "top-headlines?apiKey=" + apiKey;
        } else if(!settings.getString("country", "undef").equals("undef")) {
            SupportedCountries supportedCountries = new SupportedCountries();
            query = url + "top-headlines?country=" + supportedCountries.getCode(settings.getString("country", "")) + "&apiKey=" + apiKey;
        } else if(!settings.getString("category", "undef").equals("undef")) {

        } else if(!settings.getString("source", "undef").equals("undef")) {

        } else if(!settings.getString("phrase", "undef").equals("undef")) {

        }

        queue = Volley.newRequestQueue(context);

        stringRequest = new StringRequest(Request.Method.GET, urlTest,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String title = "Didn't parse";
                        String content = "Content";
                        try {
                            JSONObject reader = new JSONObject(response);
                            JSONArray articles = reader.getJSONArray("articles");
                            reader = articles.getJSONObject(0);
                            title = reader.getString("title");
                            content = reader.getString("content");
                        } catch (JSONException e) {
                            Toast.makeText(context, "Error parsing json", Toast.LENGTH_LONG).show();
                        }

                        Intent intent = new Intent(context, TTS.class);
                        intent.putExtra("message", title);
                        context.startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void query() {
        queue.add(stringRequest);
    }
}
