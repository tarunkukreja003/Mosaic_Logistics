package com.example.tarunkukreja.event_log_sponsor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.example.tarunkukreja.event_log_sponsor.Database.CategoryActivity;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 07/07/17.
 */

public class SubCategoryAdapter extends ArrayAdapter<CustomClass> {
    public SubCategoryAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public SubCategoryAdapter(Context context, ArrayList<CustomClass> arrayList){
        super(context, 0, arrayList);
    }



    public class CategoryViewHolder{
        TextView product_name ;
        ImageView log_back_image ;
        View rootView ;



        public CategoryViewHolder(View view){

            rootView = view ;
            product_name = (TextView) view.findViewById(R.id.category_text) ;
            log_back_image = (ImageView)view.findViewById(R.id.logis_back_image) ;

        }
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, final @NonNull ViewGroup parent) {

        Typeface mRoboto_medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf") ;
        Typeface mRoboto_reg = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf") ;

        CustomClass customClass = getItem(position) ;
        CategoryViewHolder categoryViewHolder = null ;

        if(convertView == null) {
            Log.d("Custom Adapter", "convertView is null") ;
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sub_category_layout, parent, false);
            categoryViewHolder = new CategoryViewHolder(convertView) ;
            convertView.setTag(categoryViewHolder);

        }else{
            Log.d("Custom Adapter", "convertView is not null") ;
            categoryViewHolder = (CategoryViewHolder) convertView.getTag() ;
        }

        assert customClass != null;
        categoryViewHolder.product_name.setText(customClass.getVenue_name());
        categoryViewHolder.product_name.setTypeface(mRoboto_medium);
        final CategoryViewHolder finalCategoryViewHolder = categoryViewHolder;
        AndroidNetworking.get(customClass.getImage())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsBitmap(new BitmapRequestListener() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        finalCategoryViewHolder.log_back_image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Adapter", "Error loading images") ;
                    }
                });



        switch (position){

            case 1:
                Log.d("Adapter", "Case 1: " + position) ;
                categoryViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CategoryActivity.class);
                    intent.putExtra("the_category", "Photography & Videography");
                    getContext().startActivity(intent);
                }
            });
                break;

            case 2 :
                Log.d("Adapter", "Case 2: " + position) ;
                categoryViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CategoryActivity.class);
                    intent.putExtra("the_category", "Food & Beverages");
                    getContext().startActivity(intent);
                }
            });
                break;

            default :

                break;

        }
        return convertView;
    }
}

