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
        sqLiteDatabase.execSQL("CREATE TABLE question_bank (questionId       integer  PRIMARY KEY," +
                "                                           question         varchar(200)," +
                "                                           correctAnswerId  integer REFERENCES answer_bank(answerId) )");

        //Create answer_bank table
        sqLiteDatabase.execSQL("CREATE TABLE answer_bank (answerId      integer," +
                "                                         questionId    integer REFERENCES question_bank(questionId)," +
                "                                         answer        varchar(30)," +
                "                                         PRIMARY KEY(answerId, questionId))");

        //insert data to the database at the point of database creation after tables are created
        insertAllQuestions(sqLiteDatabase);
        insertAllAnswers(sqLiteDatabase);
        updateAllCorrectAnswers(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion){
    }

    /***
     * Performs a single Question Insertion to the database
     * @param db
     * @param questionId
     * @param question
     */
    private void insertQuestion(SQLiteDatabase db, int questionId, String question){
        ContentValues cv = new ContentValues(2);

        cv.put("questionId", questionId);
        cv.put("question", question);

        db.insert("question_bank", null, cv);
        //db.close();
    }

    /***
     * Insert all questions to the database
     * @param db
     */
    private void insertAllQuestions(SQLiteDatabase db){
        insertQuestion(db, 11, "There are 7 horcruxes in Harry Potter movie. Out of these which is not a horcrux?");
        insertQuestion(db, 12, "What is the name of the place where Harry Potter’s parents lived?");
        insertQuestion(db, 13, "What power did Bella get after she transformed to a vampire?");
        insertQuestion(db, 14, "What is the movie that has a group of youngsters chasing after mysteries around the world and solving them out?");
        insertQuestion(db, 15, "In which movie shows that knowing the future and trying to change it will not going to save the friends from their tragic deaths?");
        insertQuestion(db, 16, "What are John Abraham and Akshay Kumar’s professions in Garam Masala?");
        insertQuestion(db, 17, "What is Shah Rukh’s mantra to woo a girl in Kal Ho Na Hoo?");
        insertQuestion(db, 18, "In Harry Potter, who plays Nearly Headless Nick in the movie?");
        insertQuestion(db, 19, "In Harry Potter, who plays Hagrid in the movie?");
        insertQuestion(db, 20, "In ‘The Sorcerer’s Stone’ besides a cake, what does Hagrid get Harry for his birthday?");
        insertQuestion(db, 21, "In‘The Sorcerer’s Stone’ who is the first person to be sorted into their houses?");
        insertQuestion(db, 22, "In Sorcerer’s Stone how many last points does Gryffindor get form Dumbledore?");
        insertQuestion(db, 23, "In Harry Potter, what are the numbers on the front of the Hogwart’s Express?");
        insertQuestion(db, 24, "In “Goblet of Fire” who did Krum save in his second task?");
        insertQuestion(db, 25, "Who directed the movie Harry Potter and the Goblet of Fire?");
        insertQuestion(db, 26, "What is the horror movie where a disfigured midnight mangler who preys on the teenagers on their dreams?");
        insertQuestion(db, 27, "What is the movie that makes fear about to have twin births?");
        insertQuestion(db, 28, "The following dialogue is from which movie? “I’m gonna make him an offer he can’t refuse”");
        insertQuestion(db, 29, "The following dialogue is from which movie? “With great power comes great responsibility.”");
        insertQuestion(db, 30, "Which is not a movie,written by Roald Dahl?");
    }

    /***
     * Performs a single Answer Insertion to the database
     * @param db
     * @param answerId
     * @param questionId
     * @param answer
     */
    private void insertAnswer(SQLiteDatabase db, int answerId, int questionId, String answer){
        ContentValues cv = new ContentValues(3);

        cv.put("answerId", answerId);
        cv.put("questionId", questionId);
        cv.put("answer", answer);

        db.insert("answer_bank", null, cv);
        //db.close();
    }

    /***
     * Insert all answers to the database
     * @param db
     */
    private void insertAllAnswers(SQLiteDatabase db){
        insertAnswer(db, 1, 11, "The lost diadem of Ravenclaw");
        insertAnswer(db, 2, 11, "Harry Potter");
        insertAnswer(db, 3, 11, "Nagini");
        insertAnswer(db, 4, 11, "The sword of Godric Gryffindor");
        insertAnswer(db, 1, 12, "Privet Drive");
        insertAnswer(db, 2, 12, "Near the woods");
        insertAnswer(db, 3, 12, "Godric's Hollow");
        insertAnswer(db, 4, 12, "Lighthouse");
        insertAnswer(db, 1, 13, "Mind reader");
        insertAnswer(db, 2, 13, "Fast runner");
        insertAnswer(db, 3, 13, "Shielder");
        insertAnswer(db, 4, 13, "Electric power");
        insertAnswer(db, 1, 14, "Secret seven");
        insertAnswer(db, 2, 14, "Famous five");
        insertAnswer(db, 3, 14, "Sherlock Homes");
        insertAnswer(db, 4, 14, "Scooby Doo");
        insertAnswer(db, 1, 15, "Final Destination");
        insertAnswer(db, 2, 15, "Flash");
        insertAnswer(db, 3, 15, "Arrow");
        insertAnswer(db, 4, 15, "Krish");
        insertAnswer(db, 1, 16, "Reporters");
        insertAnswer(db, 2, 16, "Photographers");
        insertAnswer(db, 3, 16, "Professors");
        insertAnswer(db, 4, 16, "Lawyers");
        insertAnswer(db, 1, 17, "Saat din ladki in");
        insertAnswer(db, 2, 17, "Ek din ladki in");
        insertAnswer(db, 3, 17, "Che din ladki in");
        insertAnswer(db, 4, 17, "Do din ladki in");
        insertAnswer(db, 1, 18, "Nicholas Cage");
        insertAnswer(db, 2, 18, "John Hurt");
        insertAnswer(db, 3, 18, "John Cleese");
        insertAnswer(db, 4, 18, "Nick Nolte");
        insertAnswer(db, 1, 19, "Robbie Coltrane");
        insertAnswer(db, 2, 19, "John Cleese");
        insertAnswer(db, 3, 19, "John Coltrane");
        insertAnswer(db, 4, 19, "Robert DeNiro");
        insertAnswer(db, 1, 20, "A wand");
        insertAnswer(db, 2, 20, "An owl");
        insertAnswer(db, 3, 20, "A broomstick");
        insertAnswer(db, 4, 20, "Books");
        insertAnswer(db, 1, 21, "Draco Malfoy");
        insertAnswer(db, 2, 21, "Hermione Granger");
        insertAnswer(db, 3, 21, "Ron Weaseley");
        insertAnswer(db, 4, 21, "Harry Potter");
        insertAnswer(db, 1, 22, "200");
        insertAnswer(db, 2, 22, "170");
        insertAnswer(db, 3, 22, "50");
        insertAnswer(db, 4, 22, "100");
        insertAnswer(db, 1, 23, "5972");
        insertAnswer(db, 2, 23, "5897");
        insertAnswer(db, 3, 23, "3096");
        insertAnswer(db, 4, 23, "3702");
        insertAnswer(db, 1, 24, "Hermione");
        insertAnswer(db, 2, 24, "Igor Karkaoff");
        insertAnswer(db, 3, 24, "His Brother");
        insertAnswer(db, 4, 24, "Ron");
        insertAnswer(db, 1, 25, "Stuart Craig");
        insertAnswer(db, 2, 25, "David Barron");
        insertAnswer(db, 3, 25, "David Heyman");
        insertAnswer(db, 4, 25, "Mike Newell");
        insertAnswer(db, 1, 26, "The Unborn");
        insertAnswer(db, 2, 26, "Halloween");
        insertAnswer(db, 3, 26, "Friday the 13th");
        insertAnswer(db, 4, 26, "A nightmare on elm street");
        insertAnswer(db, 1, 27, "Halloween");
        insertAnswer(db, 2, 27, "Friday the 13th");
        insertAnswer(db, 3, 27, "The Unborn");
        insertAnswer(db, 4, 27, "Conjuring 1");
        insertAnswer(db, 1, 28, "Gladiator");
        insertAnswer(db, 2, 28, "300");
        insertAnswer(db, 3, 28, "God Father");
        insertAnswer(db, 4, 28, "Gone with the Wind");
        insertAnswer(db, 1, 29, "Batman Begins");
        insertAnswer(db, 2, 29, "Green Lantern");
        insertAnswer(db, 3, 29, "Spider Man");
        insertAnswer(db, 4, 29, "Man of Steel");
        insertAnswer(db, 1, 30, "Charlie and the Chocolate Factory");
        insertAnswer(db, 2, 30, "The BFG");
        insertAnswer(db, 3, 30, "Chitty Chitty Bang Bang");
        insertAnswer(db, 4, 30, "None of the above");
    }

    /***
     * Performs a single foreign key: correctAnswerId Update
     * @param db
     * @param questionId
     * @param correctAnswerId
     */
    private void updateCorrectAnswer(SQLiteDatabase db, int questionId, int correctAnswerId){
        ContentValues cv = new ContentValues();

        cv.put("correctAnswerId", correctAnswerId);

        db.update("question_bank", cv, "questionId = ?", new String[]{Integer.toString(questionId)});
        //db.close();
    }

    /**
     * Update the foreign key: correctAnswerId of each question
     * @param db
     */
    private void updateAllCorrectAnswers(SQLiteDatabase db){
        updateCorrectAnswer(db, 11, 4);
        updateCorrectAnswer(db, 12, 3);
        updateCorrectAnswer(db, 13, 3);
        updateCorrectAnswer(db, 14, 4);
        updateCorrectAnswer(db, 15, 1);
        updateCorrectAnswer(db, 16, 2);
        updateCorrectAnswer(db, 17, 3);
        updateCorrectAnswer(db, 18, 3);
        updateCorrectAnswer(db, 19, 1);
        updateCorrectAnswer(db, 20, 2);
        updateCorrectAnswer(db, 21, 2);
        updateCorrectAnswer(db, 22, 2);
        updateCorrectAnswer(db, 23, 1);
        updateCorrectAnswer(db, 24, 1);
        updateCorrectAnswer(db, 25, 4);
        updateCorrectAnswer(db, 26, 4);
        updateCorrectAnswer(db, 27, 3);
        updateCorrectAnswer(db, 28, 3);
        updateCorrectAnswer(db, 29, 3);
        updateCorrectAnswer(db, 30, 4);
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
                "                     WHERE qb.questionId = ab.questionId" +
                "                     AND qb.correctAnswerId = ab.answerId" +
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
