package com.ctse.sliit.moviequizapp.controllers;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ctse.sliit.moviequizapp.R;
import com.ctse.sliit.moviequizapp.helper_services.DBHelper;

import java.util.List;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //get quiz set selected by the user and the mark scored by the user
        int selectedQuizSet = getIntent().getExtras().getInt("selectedQuizSet");
        int markScored = getIntent().getExtras().getInt("markScored");
        ((TextView)findViewById(R.id.scoreTextView)).setText(Integer.toString(markScored));
        List<Integer> wrongQuestionIds = getIntent().getIntegerArrayListExtra("wronglyAnsweredQuestionIds");

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(this);

        DBHelper dbHelper = new DBHelper(this);

        TableLayout tbl = (TableLayout) findViewById(R.id.wronglyAnsweredDetailsTable);

        if (markScored != 10) {
            tbl.setColumnShrinkable(0, true);
            TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            int paddingPixels = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
            int textPixels = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics());

            for(int i = 0; i < wrongQuestionIds.size(); i++) {
                TableRow row = new TableRow(this);
                row.setLayoutParams(rowLayoutParams);

                TextView questionTxt = new TextView(this);
                questionTxt.setText(dbHelper.getQuestion(wrongQuestionIds.get(i)));
                questionTxt.setPadding(paddingPixels, paddingPixels, paddingPixels, paddingPixels);
                questionTxt.setTextColor(Color.WHITE);
                questionTxt.setTextSize(textPixels);
                row.addView(questionTxt);

                TextView correctAnswerTxt = new TextView(this);
                correctAnswerTxt.setText(dbHelper.getCorrectAnswer(wrongQuestionIds.get(i)));
                correctAnswerTxt.setPadding(paddingPixels, paddingPixels, paddingPixels, paddingPixels);
                correctAnswerTxt.setTextColor(Color.WHITE);
                correctAnswerTxt.setTextSize(textPixels);
                row.addView(correctAnswerTxt);

                tbl.addView(row);
            }
        }
        else {
            tbl.setVisibility(View.INVISIBLE);
            ((TextView)findViewById(R.id.wrongAnswerMsgTextView)).setVisibility(View.INVISIBLE);
        }

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
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
