package com.wpam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnSet;
    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), false);
            edit.apply();
            this.startActivity(new Intent(this, UserConfig.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        btnSet = findViewById(R.id.btnSetAlarm);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);

        btnSet.setOnClickListener(v -> {
            NewsApiRequest newsApiRequest = new NewsApiRequest(MainActivity.this);
            newsApiRequest.query();

            Calendar cal;
                cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                cal.set(Calendar.MONTH, datePicker.getMonth());
                cal.set(Calendar.HOUR, timePicker.getHour());
                cal.set(Calendar.MINUTE, timePicker.getMinute());

                Intent intent = new Intent(MainActivity.this, Alarm.class);
                PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC,cal.getTimeInMillis(),p1);

            Toast.makeText(MainActivity.this, "Alarm set", Toast.LENGTH_LONG).show();
        });
    }
}
