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

/**
 * Created by tarunkukreja on 08/07/17.
 */

public class VenueAdapter extends ArrayAdapter<CustomClass> {


    public VenueAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public class VenueViewHolder{
        TextView venue_name ;
        TextView venue_price ;




        public VenueViewHolder(View view){
            venue_name = (TextView) view.findViewById(R.id.event_setup_category_text) ;
            venue_price = (TextView) view.findViewById(R.id.event_setup_category_price) ;

        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CustomClass customClass = getItem(position) ;
        VenueViewHolder venueViewHolder = null ;

        if(convertView == null) {
            Log.d("VenueHeaderAdapter", "convertView is null") ;
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.venue_sub_category, parent, false);
            venueViewHolder = new VenueViewHolder(convertView) ;
            convertView.setTag(venueViewHolder);

        }else{
            Log.d("VenueHeaderAdapter", "convertView is not null") ;
            venueViewHolder = (VenueViewHolder) convertView.getTag() ;
        }

        assert customClass != null;
        venueViewHolder.venue_name.setText(customClass.getVenue_name());
        venueViewHolder.venue_price.setText(customClass.getVenue_price());

        return convertView;
    }
}
