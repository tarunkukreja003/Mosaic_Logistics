package com.example.tarunkukreja.event_log_sponsor.Forms;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import com.example.tarunkukreja.event_log_sponsor.R;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 20/07/17.
 */

public class FormAdapter extends ArrayAdapter<FormCustomClass> {

    RadioButton selected = null ;


    public FormAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
    public FormAdapter(Context context, ArrayList<FormCustomClass> arrayList){
        super(context, 0, arrayList);
    }

    public class FormViewHolder{
       RadioButton radioButton ;


        public FormViewHolder(View view){
            radioButton = (RadioButton)view.findViewById(R.id.radioButton) ;
        }

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FormCustomClass formCustomClass = getItem(position) ;
        FormViewHolder formViewHolder = null ;
        if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.form_radio_button, parent, false);
                formViewHolder = new FormViewHolder(convertView) ;
                convertView.setTag(formViewHolder);
            }else {
            formViewHolder = (FormViewHolder)convertView.getTag() ;
        }

        final FormViewHolder finalFormViewHolder = formViewHolder;
        finalFormViewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(selected != null){
                    selected.setChecked(false);
                }
                finalFormViewHolder.radioButton.setChecked(true);
                selected = finalFormViewHolder.radioButton ;
            }
        });

        formViewHolder.radioButton.setText(formCustomClass.getRadioButtonText());


        return convertView;
    }


}
