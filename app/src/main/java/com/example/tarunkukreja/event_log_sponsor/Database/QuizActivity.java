package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
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
    String category = null ;

    String userKey;

    Typeface roboto_med ;
    Typeface roboto_reg ;
    ColorStateList colorStateList ;


    ProgressBar progressBar ;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    int total ;
    String backToQuiz;

//    ArrayList<Question> categoryQuestList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar) ;
//        setSupportActionBar(toolbar);
        toolbar.setTitle(getIntent().getStringExtra("Category"));

       // Log.d("QuizActivity", "Category " + getIntent().getStringExtra("Category"));

        category = getIntent().getStringExtra("Category");

        db = new DatabaseHelper(this);
        hashMap = new LinkedHashMap<>();

        progressBar = (ProgressBar)findViewById(R.id.progress_bar) ;

        quesList = db.getAllQuestions(getIntent().getStringExtra("Category"), "1");

        Random rand = new Random();
        int x = rand.nextInt(400000);
        userKey = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + x;

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
        progressStatus = 0 ;
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(radioGroup.getCheckedRadioButtonId() == -1)) {

                    DatabaseSaveDetails dbSave = new DatabaseSaveDetails(getApplicationContext());
                    // String userkey, String question, String category, String option, String questionnumber
                    dbSave.createActivity(userKey, txtQuestion.getText().toString(), getIntent().getStringExtra("Category"), option, "0");

                    if (checkedRadioButtonId.matches("100")) {

//                        if(backToQuiz.equals("true")){
//                            Intent intent = new Intent(getApplicationContext(), ShowFinalResult.class);
//                            intent.putExtra("category", getIntent().getStringExtra("Category"));
//                            intent.putExtra("userkey", userKey);
//                            startActivity(intent);
//                            finish();
//                        }else {
                            Intent intent = new Intent(getApplicationContext(), DateLocActivity.class);
                            intent.putExtra("category", getIntent().getStringExtra("Category"));
                            intent.putExtra("userkey", userKey);
                            startActivity(intent);
                            finish();
                        //}

//                        Toast.makeText(getApplicationContext(), "no more question", Toast.LENGTH_LONG).show();
                    } else {

                        quesList = db.getAllQuestions(category, checkedRadioButtonId);
                        setQuestionView(quesList);



                            final double prog = ((double) count / (double) total) * 100;
                         // code for horizontal progress bar
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (progressStatus < prog) {
                                        // Update the progress status
                                        progressStatus += 1;

                                        // Try to sleep the thread for 20 milliseconds
//                                        try {
//                                            Thread.sleep((long) prog);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }

                                        // Update the progress bar
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressBar.setProgress(progressStatus);
                                                // Show the progress on TextView
                                                String prg_state = progressStatus + " % ";
                                                textViewCount.setText(prg_state);
                                            }
                                        });
                                    }
                                }
                            }).start();


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please Select an option", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void setQuestionView(ArrayList<Question> arrayList) {
        if (!arrayList.isEmpty()) {

//            progressStatus += 1 ;
            count++;
            radioGroup = (RadioGroup) findViewById(R.id.options_radioGroup);
            if (!hashMap.isEmpty()) {
                hashMap.clear();
                radioGroup.removeAllViews();
            }

            total = arrayList.get(0).getTotalQuestions() ;

         //   textViewCount.setText(count + "/" + (arrayList.get(0).getTotalQuestions())); // + 1 for 1st question that decides the category
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

                radioBtn.setBackground(getResources().getDrawable(R.drawable.ripple_sel));
                radioBtn.setPadding(16, 16, 16, 16);
                RadioGroup.LayoutParams params
                        = new RadioGroup.LayoutParams(QuizActivity.this, null);
                params.setMargins(0, 0, 0, 30);
                radioBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                radioBtn.setLayoutParams(params);
                radioBtn.setTypeface(roboto_med);
                radioBtn.setTextSize(20);

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
                            radioBtn.setBackground(getResources().getDrawable(R.drawable.ripple_sel));
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
