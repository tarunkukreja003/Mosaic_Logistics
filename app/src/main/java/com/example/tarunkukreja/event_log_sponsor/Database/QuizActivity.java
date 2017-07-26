package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.LinkedHashMap;
import java.util.Map;


public class QuizActivity extends AppCompatActivity {
    ArrayList<Question> quesList;
    int count = 1;
    String option;
    String checkedRadioButtonId;
    RadioButton radioBtn;

    TextView txtQuestion;
    RadioGroup radioGroup;
    TextView butNext;
    DatabaseHelper db;
    Map<String, Integer> hashMap;

    TextView textViewCount;
    String category = null ;

    String userKey;

    Typeface roboto_med ;
    Typeface roboto_reg ;
    ColorStateList colorStateList ;
//    ArrayList<Question> categoryQuestList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);


        db = new DatabaseHelper(this);
        hashMap = new LinkedHashMap<>();

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);

        Bundle bundle=getIntent().getExtras();
        if(!(bundle==null)){
             category=bundle.getString("category");
            userKey=bundle.getString("userkey");
            quesList = db.getAllQuestions(category, "1");


            toolbar.setTitle(category);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        }







        roboto_med =  Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        roboto_reg =  Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");



         colorStateList = new ColorStateList(
                new int[][]{

                        new int[]{android.R.attr.state_enabled}, //disabled
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[] {

                        getResources().getColor(R.color.colorPrimary) //disabled
                        ,getResources().getColor(R.color.colorPrimary)//enabled

                }
        );


//        currentQ=quesList.get(qid);
        textViewCount = (TextView) findViewById(R.id.form_questionNumber);
        txtQuestion = (TextView) findViewById(R.id.question_text);
        txtQuestion.setTypeface(roboto_med);

        butNext = (TextView) findViewById(R.id.next_click);
//        categoryQuestionView(categoryQuestList);
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
                        count++;
                        quesList = db.getAllQuestions(category, checkedRadioButtonId);
                        setQuestionView(quesList);
                       // Toast.makeText(getApplicationContext(),"hi"+quesList,Toast.LENGTH_LONG).show();
//                        categoryQuestList = db.categoryList() ;
//                        categoryQuestionView(categoryQuestList);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void setQuestionView(ArrayList<Question> arrayList) {
        if (!arrayList.isEmpty()) {


            radioGroup = (RadioGroup) findViewById(R.id.options_radioGroup);
            if (!hashMap.isEmpty()) {
                hashMap.clear();
                radioGroup.removeAllViews();
            }

            textViewCount.setText(count + "/" + (arrayList.get(0).getTotalQuestions())); // + 1 for 1st question that decides the category
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
                radioBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                radioBtn.setBackground(getResources().getDrawable(R.drawable.ripple_sel));
                radioBtn.setPadding(16, 16, 16, 16);
                RadioGroup.LayoutParams params
                        = new RadioGroup.LayoutParams(QuizActivity.this, null);
                params.setMargins(0, 0, 0, 30);
                radioBtn.setLayoutParams(params);
                radioBtn.setTypeface(roboto_reg);
                radioBtn.setTextSize(20);
                radioBtn.setHighlightColor(getResources().getColor(R.color.colorAccent));
//                if(radioBtn.isChecked()){
//                    radioBtn.setTypeface(roboto_med);
//                }

                if(Build.VERSION.SDK_INT>=21){
                    radioBtn.setButtonTintList(colorStateList);
                    radioBtn.invalidate(); //could not be necessary
                }
//                radioBtn.setButtonDrawable(getResources().getDrawable(R.drawable.form_selector));


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

                            //Toast.makeText(getApplicationContext(),option+"  "+checkedRadioButtonId,Toast.LENGTH_LONG).show();
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

//    private void categoryQuestionView(ArrayList<Question> categoryQuestList){
//        if(!categoryQuestList.isEmpty()){
//            if (!category_hashMap.isEmpty()) {
//                category_hashMap.clear();
//                radioGroup.removeAllViews();
//            }
//
//            txtQuestion.setText(categoryQuestList.get(0).getQuestion());
//
//            Gson gson = new Gson();
//            String json = categoryQuestList.get(0).getOption();
//            Type type = new TypeToken<LinkedHashMap<String, String>>() {}.getType();
//            category_hashMap = gson.fromJson(json, type);
//
//            int i = 0;
//            for (Map.Entry map : category_hashMap.entrySet()) {
//                i++;
//                radioBtn = new RadioButton(this);
//                radioBtn.setText(map.getKey().toString());
//                radioBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
//                radioBtn.setBackground(getResources().getDrawable(R.drawable.ripple_sel));
//                radioBtn.setPadding(16, 16, 16, 16);
//                RadioGroup.LayoutParams params
//                        = new RadioGroup.LayoutParams(QuizActivity.this, null);
//                params.setMargins(0, 0, 0, 20);
//                radioBtn.setLayoutParams(params);
//                radioBtn.setTypeface(roboto_reg);
//                radioBtn.setTextSize(20);
//                radioBtn.setHighlightColor(getResources().getColor(R.color.colorAccent));
////                if(radioBtn.isChecked()){
////                    radioBtn.setTypeface(roboto_med);
////                }
//
//                if(Build.VERSION.SDK_INT>=21){
//                    radioBtn.setButtonTintList(colorStateList);
//                    radioBtn.invalidate(); //could not be necessary
//                }
////                radioBtn.setButtonDrawable(getResources().getDrawable(R.drawable.form_selector));
//
//
//                String value = map.getValue().toString();
//                int intValue = Integer.parseInt(value);
//                String sum = intValue + "" + i;
//                radioBtn.setId(Integer.parseInt(sum));
//                radioGroup.addView(radioBtn);
//
//
//            }
//
//            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup rg, int checkedId) {
//                    for (int i = 0; i < rg.getChildCount(); i++) {
//                        radioBtn = (RadioButton) rg.getChildAt(i);
//                        if (radioBtn.getId() == checkedId) {
//                            category = radioBtn.getText().toString();
//
//                            return;
//                        }
//                    }
//                }
//            });
//        }
//    }


}
