package com.example.tarunkukreja.event_log_sponsor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by tarunkukreja on 07/07/17.
 */

public class VenueBookingFragment extends Fragment {

    ListView listView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.subcategory_grid, container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        listView = (ListView) view.findViewById(R.id.list_view) ;
//        ArrayList<CustomClass> list = new ArrayList<>() ;
//        list.add(new CustomClass("Co-Working")) ;
//        list.add(new CustomClass("Hotels")) ;
//        list.add(new CustomClass("Theatre")) ;
//        list.add(new CustomClass("Stadium")) ;
//        list.add(new CustomClass("Meeting Room")) ;
//        list.add(new CustomClass("Universities")) ;
//        list.add(new CustomClass("Banquets")) ;
//        list.add(new CustomClass("Farm House")) ;
//        list.add(new CustomClass("Outdoor Venues")) ;
//        list.add(new CustomClass("Convention Centres")) ;
//        list.add(new CustomClass("Conference Centres")) ;
//
//        VenueHeaderAdapter categoryAdapter = new VenueHeaderAdapter(getActivity(), list) ;
//        listView.setAdapter(categoryAdapter);
    }
}
