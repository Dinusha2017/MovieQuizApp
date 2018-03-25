package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
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

        //get mark scored by the user
        int markScored = getIntent().getExtras().getInt("markScored");
        ((TextView)findViewById(R.id.scoreTextView)).setText(Integer.toString(markScored));

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(this);
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
