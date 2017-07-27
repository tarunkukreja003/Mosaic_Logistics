package com.example.tarunkukreja.event_log_sponsor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.example.tarunkukreja.event_log_sponsor.SubCategory.SubCategoryActivity;

import java.util.List;

/**
 * Created by tarunkukreja on 07/07/17.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    private List<CustomClass> arraylist;
    private Context context;
    private Typeface mRoboto_black;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_name ;
        ImageView log_back_image ;



        public MyViewHolder(View view) {
            super(view);


            product_name = (TextView) view.findViewById(R.id.category_text) ;
            log_back_image = (ImageView)view.findViewById(R.id.logis_back_image);





        }
    }


    public SubCategoryAdapter(Context context, List<CustomClass> arraylist) {
        this.context = context;
        this.arraylist = arraylist;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CustomClass detail = arraylist.get(position);

        if (detail != null) {

            holder.product_name.setText(detail.getVenue_name());

            AndroidNetworking.get(detail.getImage())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsBitmap(new BitmapRequestListener() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.log_back_image.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });

            holder.log_back_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, SubCategoryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("category",detail.getVenue_name());
                    context.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }
}


































