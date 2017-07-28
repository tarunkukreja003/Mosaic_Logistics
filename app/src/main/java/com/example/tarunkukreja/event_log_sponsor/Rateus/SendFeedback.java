package com.example.tarunkukreja.event_log_sponsor.Rateus;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.tarunkukreja.event_log_sponsor.R;

public class SendFeedback extends AppCompatActivity {

    RatingBar ratingbar1;
    EditText editTextTitle,editTextDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);
        ratingbar1=(RatingBar)findViewById(R.id.ratingBar1);
        editTextTitle=(EditText) findViewById(R.id.ratingTitle);
        editTextDescription=(EditText) findViewById(R.id.ratingDescription);
        ratingbar1.setMax(5);
        ratingbar1.setNumStars(5);
        ratingbar1.setRating(3);


    }

    public void SendFeedBack(View view) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        String rating=String.valueOf(ratingbar1.getRating());
        // Toast.makeText(getApplicationContext(), rating+getDeviceName()+currentapiVersion, Toast.LENGTH_LONG).show();

    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
