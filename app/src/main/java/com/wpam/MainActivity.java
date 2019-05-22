package com.wpam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView alarm;
    TextView alarmDate;
    boolean resumed = false;

    @Override
    protected void onResume() {
        super.onResume();
        resumed = true;
        SharedPreferences settings = this.getSharedPreferences("UserInfo", 0);
        int dayOfMonth = settings.getInt("DAY_OF_MONTH", 1);
        int month = settings.getInt("MONTH", 1);
        int hour = settings.getInt("HOUR", 1);
        int minute = settings.getInt("MINUTE", 1);
        String alarmTime = String.format("%02d:%02d", hour, minute);
        String alarmD = String.format("%02d.%02d", dayOfMonth, month);
        alarm.setText(alarmTime);
        alarmDate.setText(alarmD);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), true);
            edit.apply();
            this.startActivity(new Intent(this, Welcome.class));
//            this.startActivity(new Intent(this, UserConfig.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(null);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        alarm = findViewById(R.id.alarm);
        alarmDate = findViewById(R.id.alarmDate);
        SharedPreferences settings = this.getSharedPreferences("UserInfo", 0);
        int dayOfMonth = settings.getInt("DAY_OF_MONTH", 1);
        int month = settings.getInt("MONTH", 1);
        int hour = settings.getInt("HOUR", 1);
        int minute = settings.getInt("MINUTE", 1);
        String alarmTime = String.format("%02d:%02d", hour, minute);
        String alarmD = String.format("%02d.%02d", dayOfMonth, month);
        alarm.setText(alarmTime);
        alarmDate.setText(alarmD);

        alarm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
            MainActivity.this.startActivity(intent);
        });
        alarmDate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
            MainActivity.this.startActivity(intent);
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
            case R.id.action_settings:
                Intent intent = new Intent(this, UserConfig.class);
                this.startActivity(intent);
                return true;
            default:
                return true;
        }
    }
}
