package com.example.tarunkukreja.event_log_sponsor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.Database.SaveDetails;
import com.example.tarunkukreja.event_log_sponsor.R;

import java.util.List;


/**
 * Created by deepak on 27/07/17.
 */

public class FinalRecyclerAdapter extends BaseAdapter {
    private Context context;
    private List<SaveDetails> rowItems;


    int countnumber = 0;

    public FinalRecyclerAdapter(Context context, List<SaveDetails> items) {
        this.context = context;
        this.rowItems = items;
    }

    private class ViewHolder {
        TextView count, question, answer;

        // ImageView image1;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        // ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.final_recycler, null);
            holder = new ViewHolder();
            holder.count = (TextView) convertView.findViewById(R.id.finalRecyclerCount);
            holder.question = (TextView) convertView.findViewById(R.id.finalRecyclerQuestion);
            holder.answer = (TextView) convertView.findViewById(R.id.finalRecyclerAnswer);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        SaveDetails detail = rowItems.get(position);

        if (detail != null) {

            countnumber = countnumber + 1;
         //   holder.count.setText(countnumber + " .");


            holder.answer.setText("( "+detail.getOption() +" )");


            convertView.setTag(holder);
            holder.question.setText(detail.getQuestion());
        }


        return convertView;
    }


    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }


}



