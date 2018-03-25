package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ctse.sliit.moviequizapp.R;

import java.sql.BatchUpdateException;

public class QuizLevels extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_levels);

        Button button3 = findViewById(R.id.buttonLevel3);
        button3.setEnabled(false);

        Button button4 = findViewById(R.id.buttonLevel4);
        button4.setEnabled(false);

        Button button5 = findViewById(R.id.buttonLevel5);
        button5.setEnabled(false);

        Button button6 = findViewById(R.id.buttonLevel6);
        button6.setEnabled(false);

        Button button1 = findViewById(R.id.buttonLevel1);
        Button button2 = findViewById(R.id.buttonLevel2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

//    Button button1 = findViewById(R.id.buttonLevel1);
//    Button button2 = findViewById(R.id.buttonLevel2);

    public void goToQuiz(int level){
        Intent intentLevel = new Intent(QuizLevels.this, QuizActivity.class);
        intentLevel.putExtra("selectedQuizSet", level);
        startActivity(intentLevel);
    }

    @Override
    public void onClick(View v) {
     Button btn = (Button) v;
//        if (v.getId()== R.id.buttonLevel1){
//            goToQuiz(1);
//        }
//       else if (v.getId() == R.id.buttonLevel2){
//            goToQuiz(2);
//        }

        if(btn == (Button) findViewById(R.id.buttonLevel1)){
            goToQuiz(1);
        }else if(btn == (Button)findViewById(R.id.buttonLevel2)){
            goToQuiz(2);
        }
    }
}
