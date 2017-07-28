package com.example.tarunkukreja.event_log_sponsor.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by deepak on 22/07/17.
 */

public class DatabaseSaveDetails extends SQLiteOpenHelper {


    public static String TAG=DatabaseSaveDetails.class.getSimpleName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "EventMosaicSaveDeatils12";
    private static final String TABLE_QUEST = "question12";
    private static final String KEY_ID = "ID";
    private static final String KEY_USERKEY = "USERKEY";
    private static final String KEY_QUESTION = "QUESTION";
    private static final String KEY_CATEGORY = "CATEGORY";
    private static final String KEY_QUESTIONNUMBER = "QUESTIONNUMBER";
    private static final String KEY_OPTION = "OPTION";
   /// private SQLiteDatabase dbase;

    private static final String CREATE_TABLE_STEPS_SUMMARY = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERKEY
            + " TEXT, " + KEY_QUESTION
            + " TEXT, " + KEY_CATEGORY
            + " TEXT, " + KEY_OPTION
            + " TEXT, " + KEY_QUESTIONNUMBER + " TEXT)";
    ;


    public DatabaseSaveDetails(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STEPS_SUMMARY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }


    public void createActivity(String userkey, String question, String category, String option, String questionnumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USERKEY, userkey);
            values.put(KEY_QUESTION, question);
            values.put(KEY_CATEGORY, category);
            values.put(KEY_OPTION,option);
            values.put(KEY_QUESTIONNUMBER, questionnumber);

            db.insert(TABLE_QUEST, null, values);
        }catch (Exception e){
            Log.d(TAG,e.getMessage());
        }

    }

    public ArrayList<SaveDetails> getAllQuestions(String category, String userKey) {
        ArrayList<SaveDetails> quesList = new ArrayList<SaveDetails>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " WHERE " + KEY_CATEGORY + " = '" + category + "'" + " AND " + KEY_USERKEY + " = '" + userKey + "'";

        SQLiteDatabase   dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                SaveDetails quest = new SaveDetails();
                quest.setID(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                quest.setQuestion(cursor.getString((cursor.getColumnIndex(KEY_QUESTION))));
                quest.setCategory(cursor.getString((cursor.getColumnIndex(KEY_CATEGORY))));
                quest.settotalQuestions(cursor.getInt((cursor.getColumnIndex(KEY_USERKEY))));
                quest.setOption(cursor.getString((cursor.getColumnIndex(KEY_OPTION))));


                quesList.add(quest);

            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

}










        /*extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "EventMosaicSaveDeatils12";
    private static final String TABLE_QUEST = "question12";
    private static final String KEY_ID = "ID";
    private static final String KEY_USERKEY = "USERKEY";
    private static final String KEY_QUESTION = "QUESTION";
    private static final String KEY_CATEGORY = "CATEGORY";
    private static final String KEY_QUESTIONNUMBER = "QUESTIONNUMBER";
    private static final String KEY_OPTION = "OPTION";
    private SQLiteDatabase dbase;

    public DatabaseSaveDetails(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERKEY
                + " TEXT, " + KEY_QUESTION
                + " TEXT, " + KEY_CATEGORY
                + " TEXT, " + KEY_OPTION
                + " TEXT, " + KEY_QUESTIONNUMBER + " TEXT)";
        db.execSQL(sql);

        //db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(SaveDetails quest) {

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERKEY, quest.getUserkey());
        values.put(KEY_QUESTION, quest.getQuestion());
        values.put(KEY_CATEGORY, quest.getCategory());
        values.put(KEY_OPTION, quest.getOption());
        values.put(KEY_QUESTIONNUMBER, quest.getQuestionNumber());

        dbase.insert(TABLE_QUEST, null, values);
    }

    public long createActivity(String userkey, String question, String category, String option, String questionnumber) {

        ContentValues values = new ContentValues();
        values.put(KEY_USERKEY, userkey);
        values.put(KEY_QUESTION, question);
        values.put(KEY_CATEGORY, category);
        values.put(KEY_OPTION,option);
        values.put(KEY_QUESTIONNUMBER, questionnumber);


        return dbase.insert(TABLE_QUEST, null, values);
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


    public ArrayList<SaveDetails> getAllQuestions(String category, String userKey) {
        ArrayList<SaveDetails> quesList = new ArrayList<SaveDetails>();
        // Select All Query
        Log.d("cehck", "hi" + " ");
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " WHERE " + KEY_CATEGORY + " = '" + category + "'" + " AND " + KEY_USERKEY + " = '" + userKey + "'";

        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                SaveDetails quest = new SaveDetails();
                quest.setID(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                quest.setQuestion(cursor.getString((cursor.getColumnIndex(KEY_QUESTION))));
                quest.setCategory(cursor.getString((cursor.getColumnIndex(KEY_CATEGORY))));
                quest.settotalQuestions(cursor.getString((cursor.getColumnIndex(KEY_USERKEY))));
                quest.setOption(cursor.getString((cursor.getColumnIndex(KEY_OPTION))));


                quesList.add(quest);

            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

//    private String ADDQUESTION(String[] key, int[] value) {
//        Map<String, Integer> hashMap = new LinkedHashMap<>();
//        Gson gson = new Gson();
//        for (int i = 0; i < key.length; i++) {
//            hashMap.put(key[i], value[i]);
//
//        }
//
//        String json = gson.toJson(hashMap);
//        return json;
//
//    }

    public int rowcount() {
        int row = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
    }

}*/
