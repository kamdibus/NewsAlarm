package com.wpam;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class NewsApiRequest {

    private String query;
    private String url = "https://newsapi.org/v2/";
    private String apiKey;

    public NewsApiRequest(Context context) {
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

    }
}
