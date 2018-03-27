package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ctse.sliit.moviequizapp.R;
import com.ctse.sliit.moviequizapp.helper_services.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity{

    private int selectedQuizSet = 0;

    private int currentDBQuestion = 0;
    private int currentQuestionNo = 1;
    private int noOfCorrectAnswers = 0;

    private String userSelectedAnswer = "";

    private List<Integer> userAnswers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //get quiz set selected by user
        selectedQuizSet = getIntent().getExtras().getInt("selectedQuizSet");

        currentDBQuestion = Integer.parseInt(Integer.toString(selectedQuizSet) + "1");

        updateUIWithNextQuestion(false);
    }

    /***
     * Update the UI with the new question and its answers when stepping to the quiz first and when the next and back buttons are clicked later
     */
    private void updateUIWithNextQuestion(boolean isBackButton){
        //set current Question No
        ((TextView)findViewById(R.id.questionNoTextView)).setText(Integer.toString(currentQuestionNo));

        DBHelper dbHelper = new DBHelper(this);

        //set question to text view
        String question = dbHelper.getQuestion(currentDBQuestion);
        ((TextView)findViewById(R.id.questionTextView)).setText(question);

        List<String> answers = dbHelper.getAllAnswers(currentDBQuestion);

        //set answers to buttons
        Button answer1 = findViewById(R.id.answer1);
        answer1.setText(answers.get(0));

        Button answer2 = findViewById(R.id.answer2);
        answer2.setText(answers.get(1));

        Button answer3 = findViewById(R.id.answer3);
        answer3.setText(answers.get(2));

        Button answer4 = findViewById(R.id.answer4);
        answer4.setText(answers.get(3));

        Button nextBtn = findViewById(R.id.nextButton);

        Button backBtn = findViewById(R.id.backButton);

        if (currentQuestionNo == 10){
            nextBtn.setText("VIEW RESULTS");
        }
        else if(currentQuestionNo == 1){
            backBtn.setEnabled(false);
            nextBtn.setText("NEXT");
        }
        else {
            backBtn.setEnabled(true);
            nextBtn.setText("NEXT");
        }

        if(userAnswers.size() >= currentQuestionNo){
            int buttonNo = userAnswers.get(currentQuestionNo-1);

            if(buttonNo == 1){
                answer1.setBackgroundColor(Color.YELLOW);
                answer2.setBackgroundColor(Color.GRAY);
                answer3.setBackgroundColor(Color.GRAY);
                answer4.setBackgroundColor(Color.GRAY);
                userSelectedAnswer = answer1.getText().toString();
            }
            else if(buttonNo == 2){
                answer2.setBackgroundColor(Color.YELLOW);
                answer1.setBackgroundColor(Color.GRAY);
                answer3.setBackgroundColor(Color.GRAY);
                answer4.setBackgroundColor(Color.GRAY);
                userSelectedAnswer = answer2.getText().toString();
            }
            else if(buttonNo == 3){
                answer3.setBackgroundColor(Color.YELLOW);
                answer1.setBackgroundColor(Color.GRAY);
                answer2.setBackgroundColor(Color.GRAY);
                answer4.setBackgroundColor(Color.GRAY);
                userSelectedAnswer = answer3.getText().toString();
            }
            else if(buttonNo == 4){
                answer4.setBackgroundColor(Color.YELLOW);
                answer1.setBackgroundColor(Color.GRAY);
                answer2.setBackgroundColor(Color.GRAY);
                answer3.setBackgroundColor(Color.GRAY);
                userSelectedAnswer = answer4.getText().toString();
            }

            if (isBackButton && checkAnswer()){
                noOfCorrectAnswers--;
            }

            nextBtn.setEnabled(true);
        }
        else{
            answer1.setBackgroundColor(Color.GRAY);
            answer2.setBackgroundColor(Color.GRAY);
            answer3.setBackgroundColor(Color.GRAY);
            answer4.setBackgroundColor(Color.GRAY);
            nextBtn.setEnabled(false);
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
        storeUserAnswerButton(1);
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
        storeUserAnswerButton(2);
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
        storeUserAnswerButton(3);
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
        storeUserAnswerButton(4);
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
            intent.putExtra("selectedQuizSet", selectedQuizSet);
            intent.putExtra("markScored", noOfCorrectAnswers);
            finish();
            startActivity(intent);
        }

        if (currentQuestionNo <= 9){
            userSelectedAnswer = "";
            currentDBQuestion++;
            currentQuestionNo++;

            updateUIWithNextQuestion(false);
        }
    }

    /***
     * checks whether the answer selected by the user is correct
     * @return true or false
     */
    public boolean checkAnswer(){
        DBHelper dbHelper = new DBHelper(this);
        String correctAnswer = dbHelper.getCorrectAnswer(currentDBQuestion);
        if (userSelectedAnswer.equals(correctAnswer)){
            return true;
        }
        return false;
    }

    /***
     * Maintains the answer(button selected) provided by the user for each question in the list named userAnswers
     * @param selectedBtn
     */
    public void storeUserAnswerButton(int selectedBtn){
        if(userAnswers.size() >= currentQuestionNo){
            userAnswers.set(currentQuestionNo - 1, selectedBtn);
        }
        else{
            userAnswers.add(selectedBtn);
        }
    }

    /***
     * onClick method call added to the button in the xml file
     * Handles onClick event action of the Back Button
     * @param view
     */
    public void onClickBack(View view) {
        currentDBQuestion--;
        currentQuestionNo--;
        updateUIWithNextQuestion(true);
    }
}
