package com.example.tarunkukreja.event_log_sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tarunkukreja.event_log_sponsor.Forms.SampleForm;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 18/07/17.
 */

public class HomeActivity extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.subcategory_grid);


        listView = (ListView)findViewById(R.id.list_view) ;

        ArrayList<CustomClass> list = new ArrayList<>() ;
        list.add(new CustomClass("Event Setup", "","", "", "Starting at Rs.500")) ;
        list.add(new CustomClass("Transportation", "Starting at Rs.500", "Starting at Rs.700", R.string.buying_text + "Buying ",R.string.rent_text + " ")) ;
        list.add(new CustomClass("Food & Beverages", " Starting at Rs.200"," Starting at Rs.700", R.string.buying_text + "Buying ",R.string.rent_text + " ")) ;
        list.add(new CustomClass("Merchandise", "Starting at Rs.200", "Starting at Rs.700", "Buying ", "Rent ")) ;
        list.add(new CustomClass("Security", "Starting at Rs.200", "Starting at Rs.700", "Buying ", "Rent ")) ;
        list.add(new CustomClass("Venue Booking",  "Starting at Rs.200", "Starting at Rs.700", "Buying ", "Rent ")) ;
        list.add(new CustomClass("Event Planners & Management", "Starting at Rs.200", "Starting at Rs.700", "Buying ","Rent ")) ;
        list.add(new CustomClass("Photography & Videography", "Starting at Rs.200", "Starting at Rs.700", "Buying ","Rent ")) ;
        list.add(new CustomClass("Media Buying & Radio", "Starting at Rs.200", "Starting at Rs.700", "Buying ","Rent ")) ;
        list.add(new CustomClass("Parties", "Starting at Rs.200", "Starting at Rs.700", "Buying ","Rent ")) ;
        list.add(new CustomClass("Event Staffing", "Starting at Rs.200", "Starting at Rs.700", "Buying ","Rent ")) ;

        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(HomeActivity.this, list) ;
        listView.setAdapter(categoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, SampleForm.class) ;
                startActivity(intent);
            }
        });
    }
}
