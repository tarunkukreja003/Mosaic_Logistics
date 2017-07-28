package com.example.tarunkukreja.event_log_sponsor.Database;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tarunkukreja.event_log_sponsor.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 20/07/17.
 */

public class FormAdapter extends ArrayAdapter<FormCustomClass> {

    private static final String LOG_TAG = FormAdapter.class.getSimpleName();

    private RadioButton selected = null;
    private int lastCheckedPos = 0;
    int id;
    int selPos = -1;

    Context context;

    OnItemClick onItemClick;

    ArrayList<FormCustomClass> radioButtonList;
    Typeface roboto_reg ;
    Typeface roboto_med ;



    public FormAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public FormAdapter(Context context, ArrayList<FormCustomClass> arrayList) {
        super(context, 0, arrayList);
        radioButtonList = arrayList;
    }


    public class FormViewHolder {


        RadioButton radioButton;
        RadioGroup radioGroup;


        public FormViewHolder(View view) {

            radioButton = (RadioButton) view.findViewById(R.id.radioButton);
            radioGroup = (RadioGroup) view.findViewById(R.id.main_category_rg);
        }

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        Log.d(LOG_TAG, "ArrayList: " + new Gson().toJson(radioButtonList));

       roboto_med = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
       roboto_reg = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");

        FormCustomClass formCustomClass = getItem(position);

        FormViewHolder formViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.form_radio_button, parent, false);
            formViewHolder = new FormViewHolder(convertView);
            convertView.setTag(formViewHolder);
        } else {
            formViewHolder = (FormViewHolder) convertView.getTag();
        }

        final FormViewHolder finalFormViewHolder = formViewHolder;

        //for default check in first item
        finalFormViewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Adapter", "Inside Radio Button onClick");
                Log.d("Adapter", "Position of Radio Button Clicked " + position);

                if (selected != null) {
                    selected.setChecked(false);
                }

                finalFormViewHolder.radioButton.setChecked(true);

                selected = finalFormViewHolder.radioButton;

                try {
                    onItemClick = (OnItemClick) getContext();
                } catch (ClassCastException e) {
                    throw new ClassCastException(getContext().toString()
                            + " must implement OnItemClick");
                }
                onItemClick.onItemClick(position, radioButtonList);
            }
        });

        Log.d("Adapter", "text " + formCustomClass.getRadioButtonText());

        formViewHolder.radioButton.setText(formCustomClass.getRadioButtonText());
        formViewHolder.radioButton.setTypeface(roboto_med);

        return convertView;
    }


    public interface OnItemClick {
        void onItemClick(int pos, ArrayList<FormCustomClass> formCustomClassArrayList);
    }

    public int getItemPos() {
        return selPos;
    }
}



//        if(selPos == position){
//            formViewHolder.radioButton.setChecked(true);
//        }else {
//            formViewHolder.radioButton.setChecked(false);
//        }



//        finalFormViewHolder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if(isChecked){
//                    selected.setChecked(true);
//                }else {
//                    selected.setChecked(false);
//                }
//            }
//        });






//        finalFormViewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//
//
////
////                if(selected != null){
////                    selected.setChecked(false);
////                }
////
////                finalFormViewHolder.radioButton.setChecked(true);
////
////                selected = finalFormViewHolder.radioButton ;
//
//
//
//
//
//
//            }
//        });






//                View vMain = ((View) v.getParent());
//                int newIndex = ((ViewGroup) vMain.getParent()).indexOfChild(vMain);
//                if (listIndex == newIndex) return;
//
//                if (selected != null) {
//                    selected.setChecked(false);
//                }
//                selected = (RadioButton) v;
//                listIndex = newIndex;





//                notifyDataSetChanged();
//                Log.d("Adapter", "selected position " + position);
//                selPos = position ;




    //    if(selected == null){
//                    selected = (RadioButton)v ;
//                    selected.setChecked(true);
//                }
//
//                if(selected == v)
//                    return;
//
//                selected.setChecked(false);
//                ((RadioButton)v).setChecked(true);
//                selected = (RadioButton)v;








//        formViewHolder.radioButton.setChecked(position == selPos);
//        formViewHolder.radioButton.setTag(position);
//
//        formViewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selPos = (Integer)v.getTag();
//                notifyDataSetChanged();
//                Log.d("Adapter", "selected position " + selPos);
//            }
//        });







//    public void selectedButton(int index){
//        selPos = index ;
//    }



