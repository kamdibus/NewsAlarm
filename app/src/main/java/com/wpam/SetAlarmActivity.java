package com.wpam;

import android.content.Intent;
import android.content.SharedPreferences;
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
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnSet = findViewById(R.id.btnSetAlarm);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);

        btnSet.setOnClickListener(v -> {

            Calendar cal;
            cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth()-1);
            cal.set(Calendar.MONTH, datePicker.getMonth());
            cal.set(Calendar.HOUR, timePicker.getHour());
            cal.set(Calendar.MINUTE, timePicker.getMinute());
            SharedPreferences settings = this.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor edit = settings.edit();
            edit.putInt("DAY_OF_MONTH", datePicker.getDayOfMonth());
            edit.putInt("MONTH", datePicker.getMonth()+1);
            edit.putInt("HOUR", timePicker.getHour());
            edit.putInt("MINUTE", timePicker.getMinute());
            edit.apply();
//            Intent intent = new Intent(SetAlarmActivity.this, Alarm.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(),0, intent,PendingIntent.FLAG_ONE_SHOT);
//            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
//            am.set(AlarmManager.RTC,cal.getTimeInMillis(),p1);

            Toast.makeText(SetAlarmActivity.this, "Alarm set", Toast.LENGTH_SHORT).show();

            NewsApiRequest newsApiRequest = new NewsApiRequest(SetAlarmActivity.this);
            newsApiRequest.query();

            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return true;
    }
}
