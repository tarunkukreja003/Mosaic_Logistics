package com.example.tarunkukreja.event_log_sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tarunkukreja.event_log_sponsor.Forms.SampleForm;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 07/07/17.
 */

public class EventSetupFragment extends Fragment {

    ListView listView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.subcategory_grid, container, false) ;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.list_view) ;

        ArrayList<CustomClass> list = new ArrayList<>() ;
        list.add(new CustomClass("Event Setup", "","", "", "Starting at Rs.500")) ;
        list.add(new CustomClass("Transportation", "Starting at Rs.500", "Starting at Rs.700", R.string.buying_text + "Buying ",R.string.rent_text + " ")) ;
        list.add(new CustomClass("Food & Beverages", " Starting at Rs.200"," Starting at Rs.700", R.string.buying_text + "Buying ",R.string.rent_text + " ")) ;
        list.add(new CustomClass("Merchandise", "Starting at Rs.200", "Starting at Rs.700", "Buying ", "Rent ")) ;
        list.add(new CustomClass("Security", "Starting at Rs.200", "Starting at Rs.700", "Buying ", "Rent ")) ;
        list.add(new CustomClass("Venue Booking",  "Starting at Rs.200", "Starting at Rs.700", "Buying ", "Rent ")) ;
        list.add(new CustomClass("Event Management", "Starting at Rs.200", "Starting at Rs.700", "Buying ","Rent ")) ;

        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(getActivity(), list) ;
        listView.setAdapter(categoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SampleForm.class) ;
                startActivity(intent);
            }
        });
    }
}
