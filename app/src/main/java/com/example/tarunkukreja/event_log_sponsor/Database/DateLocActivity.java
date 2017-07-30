package com.example.tarunkukreja.event_log_sponsor.Database;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunkukreja.event_log_sponsor.Final.ShowFinalResult;
import com.example.tarunkukreja.event_log_sponsor.R;
import com.example.tarunkukreja.event_log_sponsor.helper.Referral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tarunkukreja on 27/07/17.
 */

public class DateLocActivity extends AppCompatActivity {

    ArrayList<SaveDetails> arrayListDateLoc;
    private static final String LOG_TAG = DateLocActivity.class.getSimpleName();

    EditText date_editText;
    EditText loc_editText;
    EditText desc_editText;

    TextView date_text;
    TextView loc_text;
    TextView desc_text;

    LinearLayout date_lay;
    SharedPreferences.Editor edit;
    Calendar calendar;

    TextView finish_click;
    SharedPreferences dateLocation;
    Boolean hasData;
    private String userKey = null;
    private String category = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_loc);

        arrayListDateLoc = new ArrayList<>();
        Typeface roboto_med = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");


        date_editText = (EditText) findViewById(R.id.date_editText);
        loc_editText = (EditText) findViewById(R.id.loc_editText);
        desc_editText = (EditText) findViewById(R.id.additional_info_editText);


        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            category = bundle.getString("category");
            userKey = bundle.getString("userkey");
            dateLocation = getSharedPreferences(userKey + "_" + category, Context.MODE_PRIVATE);
            hasData = dateLocation.getBoolean(Referral.SHARED_BOOLEAN, false);


            if (hasData) {
                String date = dateLocation.getString(Referral.SHARED_DATE, null);
                String location = dateLocation.getString(Referral.SHARED_LOCATION, null);
                String additional = dateLocation.getString(Referral.SHARED_ADDITIONAL, null);
                date_editText.setText(date + "");
                loc_editText.setText(location + "");
                desc_editText.setText(additional + "");


            }
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_date);


        calendar = Calendar.getInstance();

        date_text = (TextView) findViewById(R.id.date_text);
        loc_text = (TextView) findViewById(R.id.loc_text);
        desc_text = (TextView) findViewById(R.id.additional_info_text);

        date_lay = (LinearLayout) findViewById(R.id.date_lin_layout);

        finish_click = (TextView) findViewById(R.id.form_finish_click);

        date_editText.setInputType(InputType.TYPE_NULL);

        date_text.setTypeface(roboto_med);
        loc_text.setTypeface(roboto_med);
        desc_text.setTypeface(roboto_med);


        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                updateText();
            }
        };

        date_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateLocActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

        date_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateLocActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

        finish_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc_text = loc_editText.getText().toString();
                String date_text = date_editText.getText().toString();
                String additional_text = desc_editText.getText().toString();
                if (TextUtils.isEmpty(loc_text)) {

                    Toast.makeText(DateLocActivity.this, "Enter location of your event", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(date_text)) {

                    Toast.makeText(DateLocActivity.this, "Enter date of your event", Toast.LENGTH_SHORT).show();
                }


                SharedPref(date_text, loc_text, additional_text);


                Intent intent = new Intent(getApplicationContext(), ShowFinalResult.class);
                intent.putExtra(Referral.CATEGORY, category);
                intent.putExtra(Referral.USERKEY, userKey);
                startActivity(intent);
                finish();


            }
        });


    }

    private void updateText() {


        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date_editText.setText(sdf.format(calendar.getTime()));
    }


    private void SharedPref(String date, String location, String additional) {

        if (userKey != null) {


            edit = dateLocation.edit();


            Boolean hasRun = dateLocation.getBoolean(Referral.SHARED_BOOLEAN, false);
            //Set<String> set = new HashSet<>();
            //Gson gson = new Gson();

            //if (!hasRun) {


            edit.putBoolean(Referral.SHARED_BOOLEAN, true);
            edit.putString(Referral.SHARED_DATE, date);
            edit.putString(Referral.SHARED_LOCATION, location);


            edit.putString(Referral.SHARED_ADDITIONAL, additional);


            edit.commit();


//            if (!hasData) {
//
//
//                SaveDetails saveDetails = new SaveDetails();
//                saveDetails.setOption(option);
//                saveDetails.setQuestion(question);
//
//                arrayListDateLoc.add(saveDetails);
//                edit.putBoolean("speed", true);
//
//                String json = gson.toJson(arrayListDateLoc);
//
//                edit.putString("array", json);
//                edit.commit();
//            } else {
//                String json = dateLocation.getString("array", null);
//                Type type = new TypeToken<ArrayList<SaveDetails>>() {
//                }.getType();
//                ArrayList<SaveDetails> arrayList = gson.fromJson(json, type);
//
//                SaveDetails saveDetails = new SaveDetails();
//                saveDetails.setOption(option);
//                saveDetails.setQuestion(question);
//
//                arrayList.add(saveDetails);
//
//                json = gson.toJson(arrayList);
//                edit.putBoolean("speed", true);
//                edit.putString("array", json);
//                edit.commit();
//
//                Log.d("hello", arrayList + "");
//            }
        }
    }
}
