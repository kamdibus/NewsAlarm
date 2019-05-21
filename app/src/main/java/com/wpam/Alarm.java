package com.wpam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Alarm extends BroadcastReceiver {

    private NewsApiRequest newsApiRequest;

    @Override
    public void onReceive(Context context, Intent intent) {
        newsApiRequest = new NewsApiRequest(context);
        newsApiRequest.query();
    }

}