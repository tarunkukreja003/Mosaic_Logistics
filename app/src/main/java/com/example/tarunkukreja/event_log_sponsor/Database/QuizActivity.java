package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunkukreja.event_log_sponsor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {
    ArrayList<Question> quesList;
    int count = 0;
    String option;
    String checkedRadioButtonId;
    RadioButton radioBtn;

    TextView txtQuestion;
    RadioGroup radioGroup;
    TextView butNext;
    DatabaseHelper db;
    Map<String, Integer> hashMap;

    TextView textViewCount;
    String category = "Videography";

    String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = new DatabaseHelper(this);
        hashMap = new LinkedHashMap<>();
        quesList = db.getAllQuestions(category, "1");
        Random rand = new Random();
        int x = rand.nextInt(400000);
        userKey = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + x;


//        currentQ=quesList.get(qid);
        textViewCount = (TextView) findViewById(R.id.quizQuestionNumber);
        txtQuestion = (TextView) findViewById(R.id.quizQuestion);

        butNext = (TextView) findViewById(R.id.nextQuestion);
        setQuestionView(quesList);
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(radioGroup.getCheckedRadioButtonId() == -1)) {

                    DatabaseSaveDetails dbSave = new DatabaseSaveDetails(getApplicationContext());
                    // String userkey, String question, String category, String option, String questionnumber
                    dbSave.createActivity(userKey, txtQuestion.getText().toString(), category, option, "0");

                    if (checkedRadioButtonId.matches("100")) {

                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("category", category);
                        intent.putExtra("userkey", userKey);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "no more question", Toast.LENGTH_LONG).show();
                    } else {
                        quesList = db.getAllQuestions(category, checkedRadioButtonId);
                        setQuestionView(quesList);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void setQuestionView(ArrayList<Question> arrayList) {
        if (!arrayList.isEmpty()) {

            count++;
            radioGroup = (RadioGroup) findViewById(R.id.quizRadioGroup);
            if (!hashMap.isEmpty()) {
                hashMap.clear();
                radioGroup.removeAllViews();
            }

            textViewCount.setText(count + "/" + arrayList.get(0).getTotalQuestions());
            txtQuestion.setText(arrayList.get(0).getQuestion());

            Gson gson = new Gson();
            String json = arrayList.get(0).getOption();
            Type type = new TypeToken<LinkedHashMap<String, Integer>>() {}.getType();
            hashMap = gson.fromJson(json, type);

            int i = 0;
            for (Map.Entry map : hashMap.entrySet()) {
                i++;
                radioBtn = new RadioButton(this);
                radioBtn.setText(map.getKey().toString());
                String value = map.getValue().toString();
                int intValue = Integer.parseInt(value);
                String sum = intValue + "" + i;
                radioBtn.setId(Integer.parseInt(sum));
                radioGroup.addView(radioBtn);
            }
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup rg, int checkedId) {
                    for (int i = 0; i < rg.getChildCount(); i++) {
                        radioBtn = (RadioButton) rg.getChildAt(i);
                        if (radioBtn.getId() == checkedId) {
                            option = radioBtn.getText().toString();

                            checkedRadioButtonId = fetchSubstring(String.valueOf(radioGroup.getCheckedRadioButtonId()));

                            return;
                        }
                    }
                }
            });
        }


    }

    private String fetchSubstring(String s) {

        String sum = "0";
        if (s.length() == 2) {
            sum = s.substring(0, 1);

            return sum;
        } else if (s.length() == 4) {
            sum = s.substring(0, 3);

            return sum;
        }


        return sum;
    }
}
