package com.wpam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Alarm extends BroadcastReceiver {

    private NewsApiRequest newsApiRequest;

    private RequestQueue queue;
    private String url = "http://www.google.com";
    private StringRequest stringRequest;

    private void perepareRequest(final Context context) {
        queue = Volley.newRequestQueue(context);

        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response.substring(0,50), Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(context, TTS.class);
//                        intent.putExtra("message", "text to read");
//                        context.startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        perepareRequest(context);

        newsApiRequest = new NewsApiRequest(context);

        queue.add(stringRequest);
    }

}