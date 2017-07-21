package com.example.tarunkukreja.event_log_sponsor.Forms;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.R;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 18/07/17.
 */

public class SampleForm extends Fragment {

    TextView qustion_text ;
    ListView radioButtonListView ;
    TextView nextClick ;

    int pos ;





    private onButtonSelected mButtonListener ;

    Typeface mRobotoMedium ;

    public static SampleForm newInstance() {
        return new SampleForm();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.form, container, false);

        radioButtonListView = (ListView)view.findViewById(R.id.radio_button_listView) ;
        nextClick = (TextView)view.findViewById(R.id.next_click) ;

        final ArrayList<FormCustomClass> formButtonList = new ArrayList<FormCustomClass>();
        formButtonList.add(new FormCustomClass("Room Setup"));
        formButtonList.add(new FormCustomClass("Sinage"));
        formButtonList.add(new FormCustomClass("jbjjj"));
        formButtonList.add(new FormCustomClass("bbbu"));
        formButtonList.add(new FormCustomClass("jjookl"));

        final ArrayList<FormCustomClass> formNextList = new ArrayList<FormCustomClass>();
        formNextList.add(new FormCustomClass("1"));
        formNextList.add(new FormCustomClass("2"));
        formNextList.add(new FormCustomClass("3"));
        formNextList.add(new FormCustomClass("4"));
        formNextList.add(new FormCustomClass("5"));

        final ArrayList<FormCustomClass> formInsteadList = new ArrayList<FormCustomClass>();
        formInsteadList.add(new FormCustomClass("7"));
        formInsteadList.add(new FormCustomClass("8"));
        formInsteadList.add(new FormCustomClass("9"));
        formInsteadList.add(new FormCustomClass("10"));
        formInsteadList.add(new FormCustomClass("11"));

        FormAdapter formAdapter = new FormAdapter(getActivity(), formButtonList) ;

        radioButtonListView.setAdapter(formAdapter);

        radioButtonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position ;
            }
        });

        nextClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == 0){
                    String question = "What is your Age";
                    mButtonListener.onButtonSelectedItem(formNextList, question);
                }else if(pos == 1) {
                    String question = "What Quantity";
                    mButtonListener.onButtonSelectedItem(formInsteadList, question);
                }
//                }else {
//                    mButtonListener.onButtonSelectedItem(formNextList);
//                }
            }
        });

        return view;


    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof onButtonSelected) {
            mButtonListener = (onButtonSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement onButtonSelected interface");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public interface onButtonSelected {
        void onButtonSelectedItem(ArrayList<FormCustomClass> formList, String question);
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.form);
//
//
//       mRobotoMedium = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
//        qustion_text.setTypeface(mRobotoMedium);
//
//    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radioButton:
//                if (checked){
//                    radioButton.setTypeface(mRobotoMedium);
//                }
//                    // Pirates are the best
//                    break;
//            case R.id.radioButton2:
//                if (checked){
//                    radioButton2.setTypeface(mRobotoMedium);
//                }
//                    // Ninjas rule
//                    break;
//            case R.id.radioButton3:
//                if (checked){
//                    radioButton3.setTypeface(mRobotoMedium);
//                }
//                // Ninjas rule
//                break;
//            case R.id.radioButton4:
//                if (checked){
//                    radioButton4.setTypeface(mRobotoMedium);
//                }
//                // Ninjas rule
//                break;
//            case R.id.radioButton5:
//                if (checked){
//                    radioButton5.setTypeface(mRobotoMedium);
//                }
//                // Ninjas rule
//                break;
//        }
//    }
}
