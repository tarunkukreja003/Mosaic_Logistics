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
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 24/07/17.
 */

public class CategoryActivity extends AppCompatActivity implements FormAdapter.OnItemClick {

    private static final String LOG_TAG = CategoryActivity.class.getSimpleName() ;

    ListView category_listView ;

    TextView next_click_btn ;
    TextView question_category_text ;


    String[] category_questions ;



    ArrayList<FormCustomClass> photo_category_list ;
    ArrayList<FormCustomClass> food_list ;

    FormAdapter formAdapter ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface roboto_med = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_reg = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar12) ;
        //        setSupportActionBar(toolbar);
        Log.d(LOG_TAG, "Category: " + getIntent().getStringExtra("the_category"));


        category_questions = getResources().getStringArray(R.array.category_questions) ;

        category_listView = (ListView) findViewById(R.id.category_listView);

        category_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        next_click_btn = (TextView) findViewById(R.id.next_click);
        question_category_text = (TextView) findViewById(R.id.question_text_category_dec);
        question_category_text.setTypeface(roboto_med);


        switch (getIntent().getStringExtra("the_category")) {
            case "Photography & Videography":

                toolbar.setTitle("Photography & Videography");

                question_category_text.setText(category_questions[0]);


                photo_category_list = new ArrayList<>();
                photo_category_list.add(new FormCustomClass("Photographer"));
                photo_category_list.add(new FormCustomClass("Videographer"));
                formAdapter = new FormAdapter(this, photo_category_list);
                category_listView.setAdapter(formAdapter);
                break;

            case "Food & Beverages" :
                toolbar.setTitle("Food & Beverages");

                question_category_text.setText(category_questions[1]);
                food_list  = new ArrayList<>();
                food_list.add(new FormCustomClass("Food Catering"));
                food_list.add(new FormCustomClass("Beverages"));
                formAdapter  = new FormAdapter(this, food_list);
                category_listView.setAdapter(formAdapter);
                break;
            default: Log.d(LOG_TAG, "Wrong Option") ;
                break;
        }



    }

    @Override
    public void onItemClick(int pos, ArrayList<FormCustomClass> formCustomClassArrayList) {

        Log.d("CategoryActivity", "Pos from Interface " + pos);
        Log.d("CategoryActivity", "ArrayList from Interface " + new Gson().toJson(formCustomClassArrayList));

        Log.d(LOG_TAG, "Position 0 text " + formCustomClassArrayList.get(0).getRadioButtonText());

            switch (formCustomClassArrayList.get(0).getRadioButtonText()) {

                case "Photographer" :


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
                        break;
                }
                break;

                case "Food Catering" :

                    switch (pos) {
                        case 0:
                            next_click_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                                    intent.putExtra("Category", "Food");
                                    startActivity(intent);
                                }
                            });
                            break;
                        case 1:
                            next_click_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                                    intent.putExtra("Category", "Beverages");
                                    startActivity(intent);
                                }
                            });
                            break;
                        default:
                            Log.d("CategoryActivity", "Wrong Option");
                            break;
                    }

                    break;


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
