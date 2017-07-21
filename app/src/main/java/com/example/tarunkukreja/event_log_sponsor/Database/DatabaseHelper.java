package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tarunkukreja on 22/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "eventMosaicQuestion";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names

    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_CATEGORY = "CATEOGORY";
    private static final String KEY_QUESTIONNUMBER = "questionid";
    private static final String KEY_OPTION = "option"; //option c

    private SQLiteDatabase dbase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_CATEGORY
                + " TEXT, " + KEY_QUESTIONNUMBER
                + " TEXT, " + KEY_OPTION + " TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    private void addQuestions() {

        String[] key1 = {"Photography", "Videography", "Event Shoot"};
        int[] value1 = {2, 3, 4};


        Question q1 = new Question("Are you looking for?", "IT", "1", ADDQUESTION(key1, value1));
        this.addQuestion(q1);


        String[] key2 = {"option3", "option4", "option5"};
        int[] value2 = {3, 4, 5};


        Question q2 = new Question("Question 2?", "IT", "2", ADDQUESTION(key2, value2));
        this.addQuestion(q2);


        String[] key3 = {"option4", "option5", "option6"};
        int[] value3 = {4, 5, 6};


        Question q3 = new Question("Question 3?", "IT", "3", ADDQUESTION(key3, value3));
        this.addQuestion(q3);

        String[] key4 = {"option5", "option6", "option7"};
        int[] value4 = {5, 6, 7};


        Question q4 = new Question("Question 4?", "IT", "4", ADDQUESTION(key4, value4));
        this.addQuestion(q4);

        String[] key5 = {"option6", "option7", "option8"};
        int[] value5 = {6, 7, 8};


        Question q5 = new Question("Question 5?", "IT", "5", ADDQUESTION(key5, value5));
        this.addQuestion(q5);

        String[] key6 = {"option7", "option8", "option1"};
        int[] value6 = {7, 8, 1};


        Question q6 = new Question("Question 6?", "IT", "6", ADDQUESTION(key6, value6));
        this.addQuestion(q6);

        String[] key7 = {"option8", "option4", "option7"};
        int[] value7 = {8, 4, 7};


        Question q7 = new Question("Question 7?", "IT", "7", ADDQUESTION(key7, value7));
        this.addQuestion(q7);

        String[] key8 = {"option3", "option7", "option1"};
        int[] value8 = {3, 7, 1};


        Question q8 = new Question("Question 8?", "IT", "8", ADDQUESTION(key8, value8));
        this.addQuestion(q8);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, quest.getQuestion());
        values.put(KEY_CATEGORY, quest.getCategory());
        values.put(KEY_QUESTIONNUMBER, quest.getQuestionNumber());
        values.put(KEY_OPTION, quest.getOption());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

//    public List<Question> getAllQuestions() {
//        List<Question> quesList = new ArrayList<Question>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
//        dbase = this.getReadableDatabase();
//        Cursor cursor = dbase.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Question quest = new Question();
//                quest.setID(cursor.getInt(0));
//                quest.setQUESTION(cursor.getString(1));
//                quest.setANSWER(cursor.getString(2));
//                quest.setOPTA(cursor.getString(3));
//                quest.setOPTB(cursor.getString(4));
//                quest.setOPTC(cursor.getString(5));
//
//                quest.setTOTAL(cursor.getString(6));
//                quest.setOption(cursor.getString(7));
//                quesList.add(quest);
//            } while (cursor.moveToNext());
//        }
//        // return quest list
//        return quesList;
//    }


    public ArrayList<Question> getAllQuestions(String category, String questionNumber) {
        ArrayList<Question> quesList = new ArrayList<Question>();
        // Select All Query
        Log.d("cehck", "hi" + " ");
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " WHERE " + KEY_CATEGORY + " = '" + category + "'" + " AND " + KEY_QUESTIONNUMBER + " = '" + questionNumber + "'";

        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Question quest = new Question();
                quest.setID(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                quest.setQuestion(cursor.getString((cursor.getColumnIndex(KEY_QUESTION))));
                quest.setCategory(cursor.getString((cursor.getColumnIndex(KEY_CATEGORY))));
                quest.setQuestionNumber(cursor.getString((cursor.getColumnIndex(KEY_QUESTIONNUMBER))));
                quest.setOption(cursor.getString((cursor.getColumnIndex(KEY_OPTION))));


                quesList.add(quest);

            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    private String ADDQUESTION(String[] key, int[] value) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Gson gson = new Gson();
        for (int i = 0; i < key.length; i++) {
            hashMap.put(key[i], value[i]);
            Log.d("enterValue", key[i] + " " + value[i]);
        }

        String json = gson.toJson(hashMap);
        return json;

    }

    public int rowcount() {
        int row = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
    }

}
