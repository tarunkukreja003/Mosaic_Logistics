package com.example.tarunkukreja.event_log_sponsor.SubCategory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.Database.DatabaseSubCategory;
import com.example.tarunkukreja.event_log_sponsor.Database.QuizActivity;
import com.example.tarunkukreja.event_log_sponsor.Database.SubCategory;
import com.example.tarunkukreja.event_log_sponsor.R;
import com.example.tarunkukreja.event_log_sponsor.helper.Referral;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class SubCategoryActivity extends AppCompatActivity {

    ArrayList<SubCategory> quesList;
    int checkedRadioButtonId;
    RadioButton radioBtn;

    TextView txtQuestion;
    RadioGroup radioGrp;
    TextView butNext;
    DatabaseSubCategory db;
    String optionSelected;

    ArrayList<String> arrayListOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        //arrayList = new ArrayList<>();
        db = new DatabaseSubCategory(this);
        arrayListOption=new ArrayList<>();
        quesList=new ArrayList<>();

       // Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);

        Bundle bundle=getIntent().getExtras();
        if(!(bundle==null)){
            String category=bundle.getString(Referral.CATEGORY);
            quesList = db.getAllQuestions(category);
         //   toolbar.setTitle(category);
         //   toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        }






//        currentQ=quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.question_textSubCategory);

        butNext = (TextView) findViewById(R.id.next_clickSubCategory);
       setQuestionView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(radioGrp.getCheckedRadioButtonId() == -1)) {
                    Random rand = new Random();
                    int x = rand.nextInt(400000);
                 String   userKey = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + x;

                    Intent intent=new Intent(getApplicationContext(), QuizActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Referral.USERKEY,userKey);
                    intent.putExtra(Referral.CATEGORY,optionSelected);
                    startActivity(intent);


                }else {

                }
            }
        });
    }


    private void setQuestionView() {




        if(quesList.size()>0 ){
            radioGrp = (RadioGroup) findViewById(R.id.options_radioGroupSubCategory);
            if (!quesList.isEmpty()) {
              //  arrayList.clear();
                radioGrp.removeAllViews();

            }


            txtQuestion.setText(quesList.get(0).getQuestion());

            Gson gson = new Gson();
            String json = quesList.get(0).getOption();
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            arrayListOption = gson.fromJson(json, type);

            Log.d("check",arrayListOption+"");


            for(int i=0;i<arrayListOption.size();i++){
                radioBtn = new RadioButton(this);

                radioBtn.setText(arrayListOption.get(i));

                radioBtn.setId(i);
                radioGrp.addView(radioBtn);
            }
            radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup rg, int checkedId) {
                    for (int i = 0; i < rg.getChildCount(); i++) {
                        radioBtn = (RadioButton) rg.getChildAt(i);
                        if (radioBtn.getId() == checkedId) {
                            optionSelected = radioBtn.getText().toString();



                            return;
                        }
                    }
                }
            });



        }


    }


}
