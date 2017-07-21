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

public class VenueHeaderAdapter extends ArrayAdapter<CustomClass> {
    public VenueHeaderAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public VenueHeaderAdapter(Context context, ArrayList<CustomClass> arrayList){
        super(context, 0, arrayList);
    }

    public class VenueHeaderViewHolder {
        TextView venue_header_name ;




        public VenueHeaderViewHolder(View view){
            venue_header_name = (TextView) view.findViewById(R.id.venue_name) ;

        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CustomClass customClass = getItem(position) ;
        VenueHeaderViewHolder venueHeaderViewHolder = null ;

        if(convertView == null) {
            Log.d("VenueHeaderAdapter", "convertView is null") ;
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.venue_category, parent, false);
            venueHeaderViewHolder = new VenueHeaderViewHolder(convertView) ;
            convertView.setTag(venueHeaderViewHolder);

        }else{
            Log.d("VenueHeaderAdapter", "convertView is not null") ;
            venueHeaderViewHolder = (VenueHeaderViewHolder) convertView.getTag() ;
        }

        assert customClass != null;
        venueHeaderViewHolder.venue_header_name.setText(customClass.getVenue_header_name());

        return convertView;
    }
}
