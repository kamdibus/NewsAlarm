package com.wpam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity {
    Button btnSet;
    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(null);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnSet = findViewById(R.id.btnSetAlarm);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);

        btnSet.setOnClickListener(v -> {
            NewsApiRequest newsApiRequest = new NewsApiRequest(SetAlarmActivity.this);
            newsApiRequest.query();

            Calendar cal;
            cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
            cal.set(Calendar.MONTH, datePicker.getMonth());
            cal.set(Calendar.HOUR, timePicker.getHour());
            cal.set(Calendar.MINUTE, timePicker.getMinute());

            Intent intent = new Intent(SetAlarmActivity.this, Alarm.class);
            PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);
            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC,cal.getTimeInMillis(),p1);

            Toast.makeText(SetAlarmActivity.this, "Alarm set", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // Some action here

        }
        return true;
    }
}
