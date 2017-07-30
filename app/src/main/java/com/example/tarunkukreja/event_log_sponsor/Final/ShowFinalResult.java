package com.example.tarunkukreja.event_log_sponsor.Final;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tarunkukreja.event_log_sponsor.Adapter.FinalAdapter;
import com.example.tarunkukreja.event_log_sponsor.Database.DatabaseSaveDetails;
import com.example.tarunkukreja.event_log_sponsor.Database.DateLocActivity;
import com.example.tarunkukreja.event_log_sponsor.Database.SaveDetails;
import com.example.tarunkukreja.event_log_sponsor.NavBarActivity;
import com.example.tarunkukreja.event_log_sponsor.R;
import com.example.tarunkukreja.event_log_sponsor.helper.Referral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowFinalResult extends AppCompatActivity {

    public static String TAG = ShowFinalResult.class.getSimpleName();
    ArrayList<SaveDetails> arrayList;
    DatabaseSaveDetails db;
    ListView listView;
    TextView textViewCategory, textViewCurrentDate,textViewSelectedDate,textViewLocation,textViewAdditional;
    SharedPreferences dateLocation;
    Boolean hasData;
    String category,userkey;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_result);
        

        allFindViewById();
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Order Summary");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        db = new DatabaseSaveDetails(getApplicationContext());
        arrayList = new ArrayList<>();

       
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
             category = bundle.getString(Referral.CATEGORY);
             userkey = bundle.getString(Referral.USERKEY);
            arrayList = db.getAllQuestions(category, userkey);


            if (arrayList.size() > 0) {
                textViewCategory.setText("My category : " + category);
                FinalAdapter categoryAdapter = new FinalAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(categoryAdapter);
                ListUtils.setDynamicHeight(listView);
            }


            dateLocation = getSharedPreferences(userkey + "_" + category, Context.MODE_PRIVATE);
            hasData = dateLocation.getBoolean(Referral.SHARED_BOOLEAN, false);

            if (hasData) {
                String date = dateLocation.getString(Referral.SHARED_DATE, null);
                String location = dateLocation.getString(Referral.SHARED_LOCATION, null);
                String additional = dateLocation.getString(Referral.SHARED_ADDITIONAL, null);
                textViewSelectedDate.setText(date + "");
                textViewLocation.setText(location + "");
                textViewAdditional.setText(additional + "");
            }

        }
    }


    
    private void allFindViewById(){
        textViewAdditional=(TextView)findViewById(R.id.showFinalAdditional);
        textViewLocation=(TextView)findViewById(R.id.showFinalLocation);
        textViewSelectedDate=(TextView)findViewById(R.id.showFinalDate);
        listView = (ListView) findViewById(R.id.showFinalListview);
        textViewCurrentDate = (TextView) findViewById(R.id.showFinalCurrentDate);
        textViewCategory = (TextView) findViewById(R.id.showFinalCategory);
        String date = new SimpleDateFormat("MMM  dd , yyyy").format(new Date());
        textViewCurrentDate.setText(date);
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }


    protected void OnClickChangeAdditionalInformation(View view){
        Intent intent = new Intent(getApplicationContext(), DateLocActivity.class);
        intent.putExtra(Referral.CATEGORY, category);
        intent.putExtra(Referral.USERKEY, userkey);
        startActivity(intent);
        finish();
    }


    @Override
    public void onDestroy(){

        ShowDialog();
        super.onDestroy();
    }


    protected void CloseShowFinal(View view){
        ShowDialog();
    }


    private void ShowDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to cancel?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(getApplicationContext(), NavBarActivity.class));
                        finish();


                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    protected void OnClickChangeNormalInformation(View view){
//        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
//        intent.putExtra(Referral.CATEGORY, "Category");
//        intent.putExtra(Referral.USERKEY, userkey);
//        intent.putExtra(Referral.BACKTOQUIZ, "true");
//        startActivity(intent);
//        finish();
    }
}
