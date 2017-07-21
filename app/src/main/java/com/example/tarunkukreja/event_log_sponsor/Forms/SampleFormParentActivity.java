package com.example.tarunkukreja.event_log_sponsor.Forms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.tarunkukreja.event_log_sponsor.R;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 20/07/17.
 */

public class SampleFormParentActivity extends FragmentActivity implements SampleForm.onButtonSelected{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_form_parent);

        if(savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, SampleForm.newInstance(), "Sample Form Fragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onButtonSelectedItem(ArrayList<FormCustomClass> formList, String question) {
        final NextFragment detailsFragment =
                NextFragment.newInstance(formList);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailsFragment, "Details Frag")
                .addToBackStack(null)
                .commit();
    }
}
