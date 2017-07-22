package com.example.tarunkukreja.event_log_sponsor.Database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tarunkukreja.event_log_sponsor.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    public static String TAG=ResultActivity.class.getSimpleName();
    ArrayList<SaveDetails> arrayList;
    DatabaseSaveDetails db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        db=new DatabaseSaveDetails(getApplicationContext());
        arrayList=new ArrayList<>();


        Bundle bundle=getIntent().getExtras();
        if(!(bundle==null)){
            String category=bundle.getString("category");
            String userkey=bundle.getString("userkey");
            arrayList=db.getAllQuestions(category,userkey);
            for(int i=0;i<arrayList.size();i++){
                Log.d(TAG,arrayList.get(i).getCategory()+" ");
                Log.d(TAG,"Question "+arrayList.get(i).getQuestion()+" "+arrayList.get(i).getOption());
               // Log.d(TAG,arrayList.get(i).getOption()+" ");
            }




        }
    }
}
