package com.example.tarunkukreja.event_log_sponsor.Database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunkukreja.event_log_sponsor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tarunkukreja on 22/07/17.
 */

public class QuizActivity extends AppCompatActivity {
    ArrayList<Question> quesList;
    int score = 0;
    int qid = 0;
    String checkedRadioButtonId;
    RadioButton radioBtn;
    // Question currentQ;
    TextView txtQuestion;
    RadioGroup radioGrp;
    TextView butNext;
    DatabaseHelper db;
    HashMap<String,Integer> hashMap;
    // ArrayList<String> arrayList;
    RadioButton[] rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //arrayList = new ArrayList<>();
        db = new DatabaseHelper(this);
        hashMap=new HashMap<>();
        quesList = db.getAllQuestions("IT", "1");

        Log.d("check",quesList+" ");


//        currentQ=quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.quizQuestion);

        butNext = (TextView) findViewById(R.id.nextQuestion);
        setQuestionView(quesList);
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // RadioButton answer=(RadioButton)findViewById(radioGrp.getCheckedRadioButtonId());
//                Log.d("yourans", currentQ.getANSWER()+" "+radioBtn.getText());
//                if(currentQ.getANSWER().equals(radioBtn.getText()))
//                {
//                    score++;
//                    Log.d("score", "Your score"+score);
//                }
                // if(qid<arrayList.size()){
                quesList = db.getAllQuestions("IT", checkedRadioButtonId);
                //currentQ=quesList.get(qid);
                setQuestionView(quesList);
//                }else{
//                    Toast.makeText(getApplicationContext(),"No more Questions",Toast.LENGTH_LONG).show();
////                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
////                    Bundle b = new Bundle();
////                    b.putInt("score", score); //Your score
////                    intent.putExtras(b); //Put your score to your next Intent
////                    startActivity(intent);
////                    finish();
//                }
            }
        });
    }


    private void setQuestionView(ArrayList<Question> arrayList) {
        if(!arrayList.isEmpty()){
            radioGrp = (RadioGroup) findViewById(R.id.quizRadioGroup);
            if (!hashMap.isEmpty()) {
                hashMap.clear();
//            radioGrp.clearCheck();
                //  radioGrp.clearCheck();
                radioGrp.removeAllViews();

            }
            txtQuestion.setText(arrayList.get(0).getQuestion());

            //create radio buttons

            Gson gson = new Gson();
            String json = arrayList.get(0).getOption();
            Type type = new TypeToken<HashMap<String,Integer>>() {
            }.getType();
            hashMap = gson.fromJson(json, type);


//        //int total=Integer.parseInt(currentQ.getTOTAL());
//        for (int i = 0; i < arrayList.size(); i++) {
//            RadioButton radioButton = new RadioButton(this);
//            radioButton.setText(arrayList.get(i));
//            radioButton.setId(i);
//            radioGrp.addView(radioButton);
//        }

            /// rb = new RadioButton[hashMap.size()];

            int i=0;
            for (Map.Entry map:hashMap.entrySet()){
                radioBtn = new RadioButton(this);
                Log.d("checkkk",map.getKey().toString() +" "+map.getValue());
                radioBtn.setText(map.getKey().toString() +" "+map.getValue());
                String value = map.getValue().toString();
                int intValue = Integer.parseInt(value);
                radioBtn.setId(intValue);
                radioGrp.addView(radioBtn);
                i++;
            }

//            int radiobuttonCount = 1;
//            for (int i = 0; i < hashMap.size(); i++) {
//                rb[i] = new RadioButton(this);
//                rb[i].setText("Radio Button " + arrayList.get(i));
//                rb[i].setId(radiobuttonCount + i);
//                // rb[i].setBackgroundResource(R.drawable.button_green);
//                radioGrp.addView(rb[i]);
//            }
            //set listener to radio button group
            radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    checkedRadioButtonId = String.valueOf(radioGrp.getCheckedRadioButtonId());

                    // radioBtn = (RadioButton) findViewById(checkedRadioButtonId);
                    Toast.makeText(QuizActivity.this, checkedRadioButtonId+" ", Toast.LENGTH_SHORT).show();
                }
            });


            // qid++;
        }


    }
}
