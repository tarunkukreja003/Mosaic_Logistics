package com.example.tarunkukreja.event_log_sponsor.Final;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.Adapter.FinalRecyclerAdapter;
import com.example.tarunkukreja.event_log_sponsor.Database.DatabaseSaveDetails;
import com.example.tarunkukreja.event_log_sponsor.Database.ResultActivity;
import com.example.tarunkukreja.event_log_sponsor.Database.SaveDetails;
import com.example.tarunkukreja.event_log_sponsor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowFinalResult extends AppCompatActivity {

    public static String TAG = ResultActivity.class.getSimpleName();
    ArrayList<SaveDetails> arrayList;
    DatabaseSaveDetails db;
    ListView listView;
    TextView textViewCategory, textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_result);
        listView = (ListView) findViewById(R.id.showFinalListview);
        textViewDate = (TextView) findViewById(R.id.showFinalDate);
        textViewCategory = (TextView) findViewById(R.id.showFinalCategory);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Order Summary");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        db = new DatabaseSaveDetails(getApplicationContext());
        arrayList = new ArrayList<>();

        String date = new SimpleDateFormat("MMM  dd , yyyy").format(new Date());
        textViewDate.setText(date);
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            String category = bundle.getString("category");
            String userkey = bundle.getString("userkey");
            arrayList = db.getAllQuestions(category, userkey);


            if (arrayList.size() > 0) {
                textViewCategory.setText("My category : " + category);
                FinalRecyclerAdapter categoryAdapter = new FinalRecyclerAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(categoryAdapter);
            }


        }
    }
}
