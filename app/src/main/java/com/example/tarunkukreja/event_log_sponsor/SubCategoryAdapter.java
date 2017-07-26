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
import com.example.tarunkukreja.event_log_sponsor.SubCategory.SubCategoryActivity;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 07/07/17.
 */

public class SubCategoryAdapter extends ArrayAdapter<CustomClass> {
    Context context;
    public SubCategoryAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context=context;
    }

    public SubCategoryAdapter(Context context, ArrayList<CustomClass> arrayList){
        super(context, 0, arrayList);
        this.context=context;
    }



    public class CategoryViewHolder{
        TextView product_name ;
        ImageView log_back_image ;
        TextView rent_price ;
        TextView buying_price ;
        TextView rent_text ;
        TextView buying_text ;



        public CategoryViewHolder(View view){
            // circular_image = (ImageView)view.findViewById(R.id.imageView_list) ;
            product_name = (TextView) view.findViewById(R.id.category_text) ;
            log_back_image = (ImageView)view.findViewById(R.id.logis_back_image) ;
//            rent_price = (TextView) view.findViewById(R.id.event_setup_category_rent_price) ;
//            buying_price = (TextView) view.findViewById(R.id.event_setup_category_buying_price) ;
//            rent_text = (TextView) view.findViewById(R.id.event_setup_category_rent_text) ;
//            buying_text = (TextView) view.findViewById(R.id.event_setup_category_buying_text) ;

        }
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Typeface mRoboto_medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf") ;
        Typeface mRoboto_reg = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf") ;

       final CustomClass customClass = getItem(position) ;
        CategoryViewHolder categoryViewHolder = null ;

        if(convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sub_category_layout, parent, false);
            categoryViewHolder = new CategoryViewHolder(convertView) ;
            convertView.setTag(categoryViewHolder);

        }else{

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

        finalCategoryViewHolder.log_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SubCategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("category",customClass.getVenue_name());
                context.startActivity(intent);
            }
        });
//        categoryViewHolder.rent_price.setText(customClass.getRent_price());
//        categoryViewHolder.buying_price.setText(customClass.getBuying_price());
//        categoryViewHolder.buying_text.setText(customClass.getBuying_text());
//        Log.d("Custom Adapter", customClass.getBuying_text()) ;
//        categoryViewHolder.rent_text.setText(customClass.getRent_text());
//        Log.d("Custom Adapter", customClass.getRent_text()) ;
//        categoryViewHolder.rent_price.setTypeface(mRoboto_medium);
//        categoryViewHolder.buying_price.setTypeface(mRoboto_medium);

//        categoryViewHolder.rent_text.setTypeface(mRoboto_reg);
//        categoryViewHolder.buying_text.setTypeface(mRoboto_reg);

        return convertView;
    }
}

