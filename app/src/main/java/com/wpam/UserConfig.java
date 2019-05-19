package com.wpam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class UserConfig extends Activity {

    CheckedTextView topHeadlines;
    Spinner countries;
    Spinner categories;
    Spinner sources;
    EditText phrase;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_config);
        topHeadlines = findViewById(R.id.topHeadlines);
        countries = findViewById(R.id.country);
        categories = findViewById(R.id.category);
        sources = findViewById(R.id.source);
        phrase = findViewById(R.id.phrase);
        save = findViewById(R.id.save);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this,R.layout.spinner_item, new SupportedCountries().getCountries()
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        countries.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<>(
                this,R.layout.spinner_item, new SupportedCategories().getCategories()
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        categories.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<>(
                this,R.layout.spinner_item, new SupportedSources().getSources()
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        sources.setAdapter(spinnerArrayAdapter);

        topHeadlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topHeadlines.isChecked()) {
                    topHeadlines.setChecked(false);
                    topHeadlines.setCheckMarkDrawable(R.drawable.check_ic);
                } else {
                    topHeadlines.setChecked(true);
                    topHeadlines.setCheckMarkDrawable(R.drawable.check);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = settings.edit();
                if(topHeadlines.isChecked()) {
                    editor.putBoolean("topHeadlines",true);
                    editor.apply();
                } else if(!countries.getSelectedItem().toString().equals("Country")) {
                    editor.putString("country",countries.getSelectedItem().toString());
                    editor.apply();
                } else if(!categories.getSelectedItem().toString().equals("Category")) {
                    editor.putString("category",categories.getSelectedItem().toString());
                    editor.apply();
                } else if(!sources.getSelectedItem().toString().equals("Source")) {
                    editor.putString("source",sources.getSelectedItem().toString());
                    editor.apply();
                } else if(!phrase.getText().toString().equals("")) {
                    editor.putString("phrase",phrase.getText().toString());
                    editor.apply();
                }
                finish();
            }
        });
    }
}
