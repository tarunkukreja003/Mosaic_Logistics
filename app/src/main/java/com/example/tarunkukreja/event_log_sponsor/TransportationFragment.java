package com.example.tarunkukreja.event_log_sponsor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 07/07/17.
 */

public class TransportationFragment extends Fragment {

    ListView listView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.subcategory_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView)view.findViewById(R.id.list_view) ;
        ArrayList<CustomClass> list = new ArrayList<>() ;
        list.add(new CustomClass("Event Setup")) ;
        list.add(new CustomClass("Transportation")) ;
        list.add(new CustomClass("Food & Beverages")) ;
        list.add(new CustomClass("Merchandise")) ;
        list.add(new CustomClass("Security")) ;
        list.add(new CustomClass("Venue Booking")) ;
        list.add(new CustomClass("Event Management")) ;



        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(getActivity(), list) ;
        listView.setAdapter(categoryAdapter);
    }
}
