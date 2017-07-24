package com.example.tarunkukreja.event_log_sponsor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 18/07/17.
 */

public class HomeActivity extends AppCompatActivity {

    ListView listView ;

    String url = "http://eventsmosaic.in/App_Assets/" ;
    String png = ".png" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidNetworking.initialize(getApplicationContext());

//        setContentView(R.layout.subcategory_grid);
//        Intent intent = new Intent(HomeActivity.this, QuizActivity.class) ;
//        startActivity(intent);
        setContentView(R.layout.subcategory_grid);

        listView = (ListView)findViewById(R.id.list_view) ;

        ArrayList<CustomClass> list = new ArrayList<>() ;
        list.add(new CustomClass("Event Setup", url + "event_setup_vendor" + png)) ;
        list.add(new CustomClass("Transportation", url + "transport_vendor" + png)) ;
        list.add(new CustomClass("Food & Beverages", url +" food_beverage_vendor"+ png)) ;
        list.add(new CustomClass("Merchandise", url +"merchandise_vendor"+ png)) ;
        list.add(new CustomClass("Security", url +"security_vendor"+ png)) ;
        list.add(new CustomClass("Venue Booking",  url +"venue_vendor"+ png)) ;
        list.add(new CustomClass("Event Planners & Management", url +"event_planning"+ png)) ;
        list.add(new CustomClass("Photography & Videography", url +"photography_vendor"+ png)) ;
        list.add(new CustomClass("Media Buying & Radio", url +"media_buying_vendor"+ png)) ;
        list.add(new CustomClass("Parties", url +"parties_vendor"+ png)) ;
        list.add(new CustomClass("Event Staffing", url +"anchor_vendor"+ png)) ;

        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(HomeActivity.this, list) ;
        listView.setAdapter(categoryAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(HomeActivity.this, QuizActivity.class) ;
//                startActivity(intent);
//            }
//        });
    }
}
