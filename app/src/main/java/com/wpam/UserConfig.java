package com.wpam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class UserConfig extends AppCompatActivity {

    Switch topHeadlines;
    Spinner countries;
    Spinner categories;
    Spinner sources;
    EditText phrase;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_config);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(null);
        topHeadlines = findViewById(R.id.topHeadlines);
        countries = findViewById(R.id.country);
        categories = findViewById(R.id.category);
        sources = findViewById(R.id.source);
        phrase = findViewById(R.id.phrase);
        save = findViewById(R.id.save);
        setFieldsVisibility(View.VISIBLE);

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
                    setFieldsVisibility(View.GONE);
                } else {
                    setFieldsVisibility(View.VISIBLE);
                }
            }
        });
        countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resetOtherSpinners("countries");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resetOtherSpinners("categories");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sources.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resetOtherSpinners("sources");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        phrase.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOtherSpinners("phrase");
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

    private void resetOtherSpinners(String spinner) {
        switch (spinner) {
            case "countries":
                categories.setSelection(0);
                sources.setSelection(0);
                break;
            case "categories":
                sources.setSelection(0);
                countries.setSelection(0);
                break;
            case "sources":
                categories.setSelection(0);
                countries.setSelection(0);
                break;
            default:
                categories.setSelection(0);
                countries.setSelection(0);
                sources.setSelection(0);
                break;
        }
    }

    public void setFieldsVisibility(int fieldsVisibility) {
        countries.setVisibility(fieldsVisibility);
        categories.setVisibility(fieldsVisibility);
        sources.setVisibility(fieldsVisibility);
        phrase.setVisibility(fieldsVisibility);
    }
}
