package com.wpam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnSet;
    EditText etVal;
    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSet = findViewById(R.id.btnSetAlarm);
        etVal = findViewById(R.id.etVal);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                cal.set(Calendar.MONTH, datePicker.getMonth());
                cal.set(Calendar.HOUR, timePicker.getHour());
                cal.set(Calendar.MINUTE, timePicker.getMinute());

                Intent intent = new Intent(MainActivity.this, Alarm.class);
                PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC,cal.getTimeInMillis(),p1);
                Toast.makeText(MainActivity.this, "Alarm set", Toast.LENGTH_LONG).show();
            }
        });
    }

}
