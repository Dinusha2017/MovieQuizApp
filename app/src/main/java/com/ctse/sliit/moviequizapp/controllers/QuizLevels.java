package com.ctse.sliit.moviequizapp.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ctse.sliit.moviequizapp.R;

import java.sql.BatchUpdateException;

import static com.ctse.sliit.moviequizapp.R.id.buttonLevel1;
import static com.ctse.sliit.moviequizapp.R.id.cancel_action;
import static com.ctse.sliit.moviequizapp.R.id.contentPanel;

public class QuizLevels extends AppCompatActivity implements View.OnClickListener{

    private final static String TAG = "lifecycle_watch";
    int lastAttemptedLevel = 0;
    Button button1, button2, button3, button4, button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_levels);

        button1 = findViewById(R.id.buttonLevel1);
        button2 = findViewById(R.id.buttonLevel2);
        button3 = findViewById(R.id.buttonLevel3);
        button4 = findViewById(R.id.buttonLevel4);
        button5 = findViewById(R.id.buttonLevel5);
        button6 = findViewById(R.id.buttonLevel6);


        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        setLastAttemptedLevel();
    }

    public void goToQuiz(int level){
        Intent intentLevel = new Intent(QuizLevels.this, QuizActivity.class);
        intentLevel.putExtra("selectedQuizSet", level);
        startActivity(intentLevel);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;

        if (btn == (Button) findViewById(R.id.buttonLevel1)) {
            goToQuiz(1);
            writeData(1);
        } else if (btn == (Button) findViewById(R.id.buttonLevel2)) {
            goToQuiz(2);
            writeData(2);
        }
    }

    public void writeData(int level){
        SharedPreferences preferences = getSharedPreferences(getResources().getString(R.string.shared_pref_filename), MODE_PRIVATE);

        if ((lastAttemptedLevel+1) == level){
            preferences.edit().putInt(getResources().getString(R.string.pref_key_level), level).apply();
            preferences.edit().putInt(getResources().getString(R.string.pref_key_score),
                        Integer.parseInt(getResources().getString(R.string.defaultLevelAndScore))).apply();
        }
    }

    public void setLastAttemptedLevel(){

        SharedPreferences preferences = getSharedPreferences(getResources().getString(R.string.shared_pref_filename), MODE_PRIVATE);
        lastAttemptedLevel = preferences.getInt(getResources().getString(R.string.pref_key_level),
                Integer.parseInt(getResources().getString(R.string.defaultLevelAndScore)));
        int highScore = preferences.getInt(getResources().getString(R.string.pref_key_score), 0);

         if(lastAttemptedLevel+1 ==2 && highScore ==10) {
            button1.setBackgroundColor((getResources().getColor(R.color.answerButtonPink)));
            button1.setEnabled(true);
            button2.setEnabled(true);
        }
        else if (lastAttemptedLevel+1 == 3 && highScore == 10){
            button1.setBackgroundColor((getResources().getColor(R.color.answerButtonPink)));
            button2.setBackgroundColor((getResources().getColor(R.color.answerButtonPink)));
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "Lifecycle Event: onRestart");
        setLastAttemptedLevel();
    }

}
