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

public class MerchandiseFrag extends Fragment {
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
//        list.add(new CustomClass("T-Shirts"," Designing & Printing starting at Rs.500")) ;
//        list.add(new CustomClass("Mugs", "Designing & Printing starting at Rs.500")) ;
//        list.add(new CustomClass("Corporate Stationary", "Designing & Printing starting at Rs.500")) ;
//        list.add(new CustomClass("Customised Merchandise", "Designing & Printing starting at Rs.500")) ;
//        list.add(new CustomClass("Gifts", "Designing & Printing starting at Rs.500")) ;
//        list.add(new CustomClass("Standees/Flexes/Banners", "Designing & Printing starting at Rs.500")) ;
//
//        MerchandiseAdapter merchandiseAdapter = new MerchandiseAdapter(getActivity(), list) ;
//        listView.setAdapter(merchandiseAdapter);
    }


}
