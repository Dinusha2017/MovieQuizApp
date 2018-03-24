package com.ctse.sliit.moviequizapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ctse.sliit.moviequizapp.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //get mark scored by the user
        int markScored = getIntent().getExtras().getInt("markScored");
        ((TextView)findViewById(R.id.scoreTextView)).setText(Integer.toString(markScored));
    }
}
