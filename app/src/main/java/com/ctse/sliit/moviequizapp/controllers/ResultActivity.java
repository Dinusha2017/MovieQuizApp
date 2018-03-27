package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ctse.sliit.moviequizapp.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //get quiz set selected by the user and the mark scored by the user
        int selectedQuizSet = getIntent().getExtras().getInt("selectedQuizSet");
        int markScored = getIntent().getExtras().getInt("markScored");
        ((TextView)findViewById(R.id.scoreTextView)).setText(Integer.toString(markScored));

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(this);

        //store the high score of the last attempted level using Shared Preferences to be used later
        SharedPreferences preferences = getSharedPreferences(getResources().getString(R.string.shared_pref_filename), MODE_PRIVATE);
        int lastAttemptedLevel = preferences.getInt(getResources().getString(R.string.pref_key_level),
                                                    Integer.parseInt(getResources().getString(R.string.defaultLevelAndScore)));

        if (selectedQuizSet == lastAttemptedLevel){
            preferences.edit().putInt(getResources().getString(R.string.pref_key_score), markScored).apply();
        }
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        if(btn == findViewById(R.id.continueButton)) {
            Intent intent = new Intent(ResultActivity.this, QuizLevels.class);
            finish();
            startActivity(intent);
        }
    }
}
