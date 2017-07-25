package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.R;
import com.example.tarunkukreja.event_log_sponsor.contactUs.contactus;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 24/07/17.
 */

public class CategoryActivity extends AppCompatActivity {


    ListView category_listView ;
    RadioButton radioButton ;
    TextView next_click_btn ;
    TextView question_category_text ;
    int pos ;
    int radioId ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        startActivity(new Intent(getApplicationContext(), contactus.class));
        radioButton = new RadioButton(this) ;

        category_listView = (ListView)findViewById(R.id.category_listView) ;
        next_click_btn = (TextView)findViewById(R.id.next_click) ;
        question_category_text = (TextView)findViewById(R.id.question_text_category_dec) ;

        question_category_text.setText("What are you looking for ?");

        final ArrayList<FormCustomClass> photo_category_list = new ArrayList<>();
        photo_category_list.add(new FormCustomClass("Photography")) ;
        photo_category_list.add(new FormCustomClass("Videography")) ;

        FormAdapter formAdapter = new FormAdapter(this, photo_category_list) ;
        category_listView.setAdapter(formAdapter);

        pos = formAdapter.getItemPos() ;

        Log.d("CategoryActivity", "Pos " + pos);

        switch (pos){
            case 0 : next_click_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class) ;
                    intent.putExtra("Category", "Photography");
                    startActivity(intent);
                }
            });
                break;
            case 1 : next_click_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class) ;
                    intent.putExtra("Category", "Videography");
                    startActivity(intent);
                }
            });
                break;
            default:
                Log.d("CategoryActivity", "Wrong Option");
        }
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


}
