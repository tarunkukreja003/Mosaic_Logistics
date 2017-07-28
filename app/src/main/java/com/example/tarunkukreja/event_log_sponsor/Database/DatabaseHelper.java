package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tarunkukreja on 22/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "EventMosaicQuestion";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names

    private static final String KEY_ID = "ID";
    private static final String KEY_QUESTION = "QUESTION";
    private static final String KEY_CATEGORY = "CATEGORY";
    private static final String KEY_TOTALQUESTIONS = "TOTALQUESTIONS";

    private static final String KEY_QUESTIONNUMBER = "QUESTIONNUMBER";

    private static final String KEY_OPTION = "OPTION";

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
                + " TEXT, " + KEY_TOTALQUESTIONS
                + " TEXT, " + KEY_OPTION
                + " TEXT, " + KEY_QUESTIONNUMBER + " TEXT)";
        db.execSQL(sql);

        addQuestions();
        addVideoGraphyData();
        addBeverageData();
        addFoodCateringData();


        //db.close();
    }

    public void addQuestions() {


        String[] key1 = {"Corporate Event", "Social Function", "College Event", "Other"};
        int[] value1 = {2, 2, 2, 2};


        Question q1 = new Question("What kind of event ?", "Photography", 4, ADDQUESTION(key1, value1), "1");
        this.addQuestion(q1);


        String[] key2 = {"2 to 3 hours", "3 to 5 hours", "More than 5 hours"};
        int[] value2 = {3, 3, 3};


        Question q2 = new Question("Duration of the Event:", "Photography", 4, ADDQUESTION(key2, value2), "2");
        this.addQuestion(q2);


        String[] key3 = {"Silver (4000 to 6000)", "Gold (7000 to 10000)", "Premium (10000 and above) "};
        int[] value3 = {4, 4, 4};


        Question q3 = new Question("Budget for Photography Service:", "Photography", 4, ADDQUESTION(key3, value3), "3");
        this.addQuestion(q3);

        String[] key9 = {"Immediately", "Within a month", "Not sure"};
        int[] value9 = {100, 100, 100};


        Question q9 = new Question("How soon do you need it ?", "Photography", 4, ADDQUESTION(key9, value9), "4");
        this.addQuestion(q9);


    }

    public void addVideoGraphyData() {


        String[] key4 = {"Wedding", "Social Function", "Corporate Event"};
        int[] value4 = {2, 2, 2};


        Question q4 = new Question("What kind of event ?", "Videography", 6, ADDQUESTION(key4, value4), "1");
        this.addQuestion(q4);

        String[] key5 = {"No I just want pictures (Starting from 5000)", "Yes I want both videos and pictures (Starting from 150000)"};
        int[] value5 = {3, 4};


        Question q5 = new Question("Do you want videos along with pictures ?", "Videography", 6, ADDQUESTION(key5, value5), "2");
        this.addQuestion(q5);


        String[] key6 = {"Normal (5000 to 8000)", " Silver (8000 to 12000)", "Gold (12000 to  15000)", "Platimum (15000 and above)"};
        int[] value6 = {5, 5, 5, 5};


        Question q6 = new Question("What is the approx budget(Per day) ?", "Videography", 6, ADDQUESTION(key6, value6), "3");
        this.addQuestion(q6);


        String[] key7 = {" Normal (20000 to 30000)", "Silver (30000 to 40000(Recommended))", "Gold (40000 to  60000)", "Platinum (60000 and above)"};
        int[] value7 = {5, 5, 5, 5};


        Question q7 = new Question("What is the approx budget(Per day) ?", "Videography", 6, ADDQUESTION(key7, value7), "4");
        this.addQuestion(q7);


        String[] key8 = {"Within 1 week", "Within 2 weeks", "Within 1 months", "Within 2 months", "Within 3 month", "Other"};
        int[] value8 = {6, 6, 6, 6, 6, 6};


        Question q8 = new Question("Prefered date for event shoot:", "Videography", 6, ADDQUESTION(key8, value8), "5");
        this.addQuestion(q8);


        String[] key10 = {"Props", "Photo/Album", "Nothing"};
        int[] value10 = {7, 7, 7};


        Question q10 = new Question("Would you like to add additional acessaries(Additional charge) ?", "Videography", 6, ADDQUESTION(key10, value10), "6");
        this.addQuestion(q10);

        String[] key9 = {"Immediately", "Within a month", "Not sure"};
        int[] value9 = {100, 100, 100};


        Question q9 = new Question("How soon do you need it ?", "Videography", 6, ADDQUESTION(key9, value9), "7");
        this.addQuestion(q9);

    }

    public void addFoodCateringData() {
        String[] key4 = {"Vegetarian", "Vegetarian and Non Vegetarian"};
        int[] value4 = {2, 2};


        Question q4 = new Question("Food Preference:", "Food", 5, ADDQUESTION(key4, value4), "1");
        this.addQuestion(q4);

        String[] key5 = {"Yes (Transportation will be charged to reach the venue)", "No"};
        int[] value5 = {3, 3};


        Question q5 = new Question("Do you want it to be cooked on the venue ?", "Food",5, ADDQUESTION(key5, value5), "2");
        this.addQuestion(q5);


        String[] key6 = {"Social Functions(Wedding ,house parties etc.)", "College/school Events", "Carnival"};
        int[] value6 = {4, 4, 4};


        Question q6 = new Question("Catering needed for:", "Food",5, ADDQUESTION(key6, value6), "3");
        this.addQuestion(q6);


        String[] key7 = {"100 to  150", "150  to 200", "200 to  250", "250  to 300", "300 to  350", "350 to  400", "400 to 450", "450 to 500", " 500 and more"};
        int[] value7 = {5, 5, 5, 5, 5, 5, 5, 5, 5};


        Question q7 = new Question("Expected number of guest ", "Food", 5, ADDQUESTION(key7, value7), "4");
        this.addQuestion(q7);


        String[] key8 = {"Basic (250 to  500)", "Standard Area (500 to 1000)", "Gold (1000 to 1300)", "Premium (1300-above)"};
        int[] value8 = {100, 100, 100, 100};


        Question q8 = new Question("Budget  per plate?", "Food", 5, ADDQUESTION(key8, value8), "5");
        this.addQuestion(q8);


    }


    public void addBeverageData() {
        String[] key4 = {"Alcohal", "Alcohal and Non alcohal", "Only water"};
        int[] value4 = {2, 2, 2};


        Question q4 = new Question("Beverage Preference", "Beverages", 2, ADDQUESTION(key4, value4), "1");
        this.addQuestion(q4);


        String[] key5 = {"1-5 crates", "6-10 crateso", "10-15 creates", " 15 and more"};
        int[] value5 = {100, 100, 100, 100};


        Question q5 = new Question("Quantity ?", "Beverages", 2, ADDQUESTION(key5, value5), "2");
        this.addQuestion(q5);


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

      //  SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, quest.getQuestion());
        values.put(KEY_CATEGORY, quest.getCategory());
        values.put(KEY_TOTALQUESTIONS, quest.gettotalQuestions());
        values.put(KEY_OPTION, quest.getOption());
        values.put(KEY_QUESTIONNUMBER, quest.getQuestionNumber());

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

    public Cursor getSearch(String field,
                            String search) {
        SQLiteDatabase sampleDB = this.getReadableDatabase();
        Cursor c = sampleDB.rawQuery("SELECT FirstName, Age FROM " + TABLE_QUEST + " where " + field + " like '%" + search + "%'", null);
        return c;
    }

    public ArrayList<Question> getAllQuestions(String category, String questionNumber) {
        ArrayList<Question> quesList = new ArrayList<Question>();
        // Select All Query
        Log.d("cehck", "hi" + " ");
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " WHERE " + KEY_CATEGORY + " = '" + category + "'" + " AND " + KEY_QUESTIONNUMBER + " = '" + questionNumber + "'";

        //String selectQuery="SELECT * FROM " + TABLE_QUEST + " where " + KEY_CATEGORY + " = '" + category + "'" + " AND " + KEY_QUESTIONNUMBER + " like '%" + questionNumber + "%'";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Question quest = new Question();
                quest.setID(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                quest.setQuestion(cursor.getString((cursor.getColumnIndex(KEY_QUESTION))));
                quest.setCategory(cursor.getString((cursor.getColumnIndex(KEY_CATEGORY))));
                quest.settotalQuestions(cursor.getInt((cursor.getColumnIndex(KEY_TOTALQUESTIONS))));
                quest.setOption(cursor.getString((cursor.getColumnIndex(KEY_OPTION))));


                quesList.add(quest);

            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    private String ADDQUESTION(String[] key, int[] value) {
        Map<String, Integer> hashMap = new LinkedHashMap<>();
        Gson gson = new Gson();
        for (int i = 0; i < key.length; i++) {
            hashMap.put(key[i], value[i]);

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

    private String addCategoryQuestion(String[] key, String[] value) {
        Map<String, String> hashMap = new LinkedHashMap<>();
        Gson gson = new Gson();
        for (int i = 0; i < key.length; i++) {
            hashMap.put(key[i], value[i]);

        }

        String json = gson.toJson(hashMap);
        return json;

    }
//
//    public ArrayList<Question> categoryList(){
//
//        ArrayList<Question> questionArrayList = new ArrayList<>() ;
//        String[] key1 = {"Photography", "Videography"} ;
//        String[] value1 = {addQuestions("1"), addVideoGraphyData("1")};
//        Question[] questArray = new Question[1] ;
//
//        String[] questions = {
//                "What are you looking for"
//        };
//        String[][] keys = {
//                key1
//        };
//        String[][] values = {
//              value1
//        };
//
//
//
//
//
//        questArray[0] = new Question(questions[0], addCategoryQuestion(keys[0], values[0]));
//
//        for(int i=0 ; i< questArray.length ; i++) {
//
//          questArray[i].setQuestion(questions[i]);
//            questArray[i].setOption(questArray[i].getOption());
//
//            questionArrayList.add(questArray[i]);
//        }
//
//        return questionArrayList ;
//
//    }

}
