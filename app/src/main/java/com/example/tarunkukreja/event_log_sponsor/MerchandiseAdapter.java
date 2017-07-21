package com.example.tarunkukreja.event_log_sponsor;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 08/07/17.
 */

public class MerchandiseAdapter extends ArrayAdapter<CustomClass> {


    public MerchandiseAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public MerchandiseAdapter(Context context, ArrayList<CustomClass> arrayList){
        super(context, 0, arrayList);
    }

    public class MerchandiseViewHolder {
        TextView merchandise_name ;
        TextView merchandise_price ;




        public MerchandiseViewHolder(View view){
            merchandise_name = (TextView) view.findViewById(R.id.merchandise_name) ;
            merchandise_price = (TextView) view.findViewById(R.id.merchandise_price) ;

        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CustomClass customClass = getItem(position) ;
        MerchandiseViewHolder merchandiseViewHolder = null ;

        if(convertView == null) {
            Log.d("MerchandiseAdapter", "convertView is null") ;
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.marchandise_layout, parent, false);
            merchandiseViewHolder = new MerchandiseViewHolder(convertView) ;
            convertView.setTag(merchandiseViewHolder);

        }else{
            Log.d("MerchandiseAdapter", "convertView is not null") ;
            merchandiseViewHolder = (MerchandiseViewHolder) convertView.getTag() ;
        }

        assert customClass != null;
        merchandiseViewHolder.merchandise_name.setText(customClass.getMerchandise_name());
        merchandiseViewHolder.merchandise_price.setText(customClass.getMerchandise_price());

        return convertView;
    }
}
