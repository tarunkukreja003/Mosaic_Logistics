package com.example.tarunkukreja.event_log_sponsor.Database;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunkukreja.event_log_sponsor.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tarunkukreja on 27/07/17.
 */

public class DateLocActivity extends AppCompatActivity {

    private static final String LOG_TAG = DateLocActivity.class.getSimpleName() ;

    EditText date_editText;
    EditText loc_editText;
    EditText desc_editText;

    TextView date_text;
    TextView loc_text;
    TextView desc_text;

    LinearLayout date_lay;

    Calendar calendar;

    TextView finish_click ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_loc);

        Typeface roboto_med = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");

        Log.d(LOG_TAG, getIntent().getStringExtra("category"));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_date) ;

        date_editText = (EditText) findViewById(R.id.date_editText);
        loc_editText = (EditText) findViewById(R.id.loc_editText);
        desc_editText = (EditText) findViewById(R.id.additional_info_editText);

        calendar = Calendar.getInstance();

        date_text = (TextView) findViewById(R.id.date_text);
        loc_text = (TextView) findViewById(R.id.loc_text);
        desc_text = (TextView) findViewById(R.id.additional_info_text);

        date_lay = (LinearLayout) findViewById(R.id.date_lin_layout);

        finish_click = (TextView)findViewById(R.id.form_finish_click) ;

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
                DatePickerDialog datePickerDialog =  new DatePickerDialog(DateLocActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

        date_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =  new DatePickerDialog(DateLocActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

        finish_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc_text = loc_editText.getText().toString() ;
                String date_text = date_editText.getText().toString() ;
                if (TextUtils.isEmpty(loc_text)) {

                    Toast.makeText(DateLocActivity.this, "Enter location of your event",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(date_text)) {

                    Toast.makeText(DateLocActivity.this, "Enter date of your event",Toast.LENGTH_SHORT).show();
                }
            }
        });








    }

    private void updateText() {


        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date_editText.setText(sdf.format(calendar.getTime()));
    }

}
