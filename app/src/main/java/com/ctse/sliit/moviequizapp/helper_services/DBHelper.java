package com.ctse.sliit.moviequizapp.helper_services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 17/03/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "moviequizapp.db";

    public DBHelper(Context context){
        super(context.getApplicationContext(), DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //Create question_bank table
        sqLiteDatabase.execSQL("CREATE TABLE question_bank (questionId       integer  PRIMARY KEY AUTOINCREMENT," +
                "                                           question         varchar(200)," +
                "                                           correctAnswerId  integer REFERENCES answer_bank(answerId) )");

        //Create answer_bank table
        sqLiteDatabase.execSQL("CREATE TABLE answer_bank (answerId      integer  PRIMARY KEY AUTOINCREMENT," +
                "                                         questionId    integer  PRIMARY KEY REFERENCES question_bank(questionId)," +
                "                                         answer        varchar(30) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion){
    }

    /***
     * Retrieve a question when the ID of that particular question is given
     * @param questionId
     * @return Question
     */
    public String getQuestion(int questionId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT question" +
                "                     FROM question_bank" +
                "                     WHERE questionId = " + questionId, null);
        cur.moveToNext();
        return cur.getString(cur.getColumnIndex("question"));
    }

    /***
     * Retrieve the correct answer when the ID of the question is given
     * @param questionId
     * @return Correct Answer
     */
    public String getCorrectAnswer(int questionId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT ab.answer" +
                "                     FROM question_bank qb, answer_bank ab" +
                "                     WHERE qb.correctAnswerId = ab.answerId" +
                "                     AND qb.questionId = " + questionId, null);
        cur.moveToNext();
        return cur.getString(cur.getColumnIndex("answer"));
    }

    /***
     * Retrieve all answers(both correct and incorrect) when the ID of that particular question is given
     * @param questionId
     * @return All Answers of a Question
     */
    public List<String> getAllAnswers(int questionId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT answer" +
                "                     FROM answer_bank" +
                "                     WHERE questionId = " + questionId, null);
        cur.moveToNext();
        int answerIdx = cur.getColumnIndex("answer");
        List<String> answers = new ArrayList<>();
        while (!cur.isAfterLast()){
            String answer = cur.getString(answerIdx);
            answers.add(answer);
            cur.moveToNext();
        }
        return answers;
    }

}
