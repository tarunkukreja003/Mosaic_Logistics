package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.R;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 24/07/17.
 */

public class CategoryActivity extends AppCompatActivity implements FormAdapter.OnItemClick {


    ListView category_listView ;
    RadioButton radioButton ;
    TextView next_click_btn ;
    TextView question_category_text ;
    int positon1 ;
    int radioId ;

    String[] category_questions ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface roboto_med = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_reg = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar12) ;
//        setSupportActionBar(toolbar);
        toolbar.setTitle("Photography/Videography");
       // toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        radioButton = new RadioButton(this);

        category_questions = getResources().getStringArray(R.array.category_questions) ;

        category_listView = (ListView) findViewById(R.id.category_listView);

        category_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        next_click_btn = (TextView) findViewById(R.id.next_click);
        question_category_text = (TextView) findViewById(R.id.question_text_category_dec);

        question_category_text.setText(category_questions[0]);
        question_category_text.setTypeface(roboto_med);

        final ArrayList<FormCustomClass> photo_category_list = new ArrayList<>();
        photo_category_list.add(new FormCustomClass("Photographer"));
        photo_category_list.add(new FormCustomClass("Videographer"));

        final FormAdapter formAdapter = new FormAdapter(this, photo_category_list);
        category_listView.setAdapter(formAdapter);

        Log.d("CategoryActivity", "Pos " + positon1);


    }

    @Override
    public void onItemClick(int pos) {

        Log.d("CategoryActivity", "Pos from Interface " + pos);

        switch (pos) {
            case 0:
                next_click_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                        intent.putExtra("Category", "Photography");
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                next_click_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                        intent.putExtra("Category", "Videography");
                        startActivity(intent);
                    }
                });
                break;
            default:
                Log.d("CategoryActivity", "Wrong Option");
        }
    }

//        next_click_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class) ;
//                intent.putExtra("Category", "Videography");
//                startActivity(intent);
//            }
//        });

//        category_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                formAdapter.selectedButton(position);
//                formAdapter.notifyDataSetChanged();
//            }
//        });



//        pos = formAdapter.getItemPos() ;




//        category_listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                pos=position ;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        radioId = formAdapter.getRadioId() ;





}
