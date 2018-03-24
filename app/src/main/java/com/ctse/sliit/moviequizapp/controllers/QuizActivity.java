package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.ctse.sliit.moviequizapp.R;
import com.ctse.sliit.moviequizapp.helper_services.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity{

    private int currentDBQuestion = 0;
    private int currentQuestionNo = 0;
    private int noOfCorrectAnswers = 0;

    private String userSelectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //get quiz set selected by user
        int selectedQuizSet = getIntent().getExtras().getInt("selectedQuizSet");

        currentDBQuestion = Integer.parseInt(Integer.toString(selectedQuizSet) + "1");

        updateUIWithNextQuestion();
    }

    /***
     * Update the UI with the new question and its answers when stepping to the quiz first and when next button is clicked later
     */
    private void updateUIWithNextQuestion(){
        //set current Question No
        currentQuestionNo = currentQuestionNo + 1;
        ((TextView)findViewById(R.id.questionNoTextView)).setText(Integer.toString(currentQuestionNo));

        DBHelper dbHelper = new DBHelper(this);

        //set question to text view
        String question = dbHelper.getQuestion(currentDBQuestion);
        ((TextView)findViewById(R.id.questionTextView)).setText(question);

        List<String> answers = new ArrayList<>();
        answers = dbHelper.getAllAnswers(currentDBQuestion);

        //set answers to buttons
        Button answer1 = findViewById(R.id.answer1);
        answer1.setBackgroundColor(Color.GRAY);
        answer1.setText(answers.get(0));

        Button answer2 = findViewById(R.id.answer2);
        answer2.setBackgroundColor(Color.GRAY);
        answer2.setText(answers.get(1));

        Button answer3 = findViewById(R.id.answer3);
        answer3.setBackgroundColor(Color.GRAY);
        answer3.setText(answers.get(2));

        Button answer4 = findViewById(R.id.answer4);
        answer4.setBackgroundColor(Color.GRAY);
        answer4.setText(answers.get(3));

        Button nextBtn = findViewById(R.id.nextButton);
        nextBtn.setEnabled(false);

        if (currentQuestionNo == 10){
            nextBtn.setText("VIEW RESULTS");
        }
    }

    /***
     * onClick method call added to the button in the xml file
     * Handles onClick event action of Answer1
     * @param view
     */
    public void onClickAnswer1(View view){
        Button btn = (Button)view;
        userSelectedAnswer = btn.getText().toString();
        btn.setBackgroundColor(Color.YELLOW);   //getResources().getColor(R.color.colorAccent)
        ((Button)findViewById(R.id.answer2)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer3)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer4)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.nextButton)).setEnabled(true);
    }

    /***
     *  onClick method call added to the button in the xml file
     *  Handles onClick event action of Answer2
     * @param view
     */
    public void onClickAnswer2(View view){
        Button btn = (Button)view;
        userSelectedAnswer = btn.getText().toString();
        btn.setBackgroundColor(Color.YELLOW);   //getResources().getColor(R.color.colorAccent)
        ((Button)findViewById(R.id.answer1)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer3)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer4)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.nextButton)).setEnabled(true);
    }

    /***
     * onClick method call added to the button in the xml file
     * Handles onClick event action of Answer3
     * @param view
     */
    public void onClickAnswer3(View view){
        Button btn = (Button)view;
        userSelectedAnswer = btn.getText().toString();
        btn.setBackgroundColor(Color.YELLOW);   //getResources().getColor(R.color.colorAccent)
        ((Button)findViewById(R.id.answer1)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer2)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer4)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.nextButton)).setEnabled(true);
    }

    /***
     * onClick method call added to the button in the xml file
     * Handles onClick event action of Answer4
     * @param view
     */
    public void onClickAnswer4(View view){
        Button btn = (Button)view;
        userSelectedAnswer = btn.getText().toString();
        btn.setBackgroundColor(Color.YELLOW);   //getResources().getColor(R.color.colorAccent)
        ((Button)findViewById(R.id.answer1)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer2)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.answer3)).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.nextButton)).setEnabled(true);
    }

    /***
     * onClick method call added to the button in the xml file
     * Handles onClick event action of the Next Button
     * @param view
     */
    public void onClickNext(View view){
        Button btn = (Button)view;

        if (checkAnswer()){
            noOfCorrectAnswers++;
        }

        if (btn.getText() == "VIEW RESULTS"){
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("markScored", noOfCorrectAnswers);
            finish();
            startActivity(intent);
        }

        if (currentQuestionNo <= 9){
            userSelectedAnswer = "";
            currentDBQuestion++;

            updateUIWithNextQuestion();
        }
    }

    /***
     * checks whether the answer selected by the user is correct
     * @return true or false
     */
    public Boolean checkAnswer(){
        DBHelper dbHelper = new DBHelper(this);
        String correctAnswer = dbHelper.getCorrectAnswer(currentDBQuestion);
        if (userSelectedAnswer.equals(correctAnswer)){
            return true;
        }
        return false;
    }
}
