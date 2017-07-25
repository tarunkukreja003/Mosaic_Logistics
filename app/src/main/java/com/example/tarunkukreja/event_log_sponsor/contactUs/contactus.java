package com.example.tarunkukreja.event_log_sponsor.contactUs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tarunkukreja.event_log_sponsor.R;
import com.example.tarunkukreja.event_log_sponsor.helper.help;

public class contactus extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        context=this;
    }

    public void ContactUsViaMail(View view) {
        help.composeEmail(context,"markitier.info@gmail.com","");
    }

    public void ContactUsViaWebsite(View view) {
        help.openWebPage(context,"http://eventsmosaic.in/");
    }

    public void contactUsViaInstagram(View view) {
    }

    public void contactUsViaFacebook(View view) {
    }

    public void contactUsViaTwitter(View view) {
    }

    public void contactUsViaPinterest(View view) {
    }

    public void contactUsViaTumblr(View view) {
    }

    public void contactUsViaCall(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+"+91-11-4986-5206"));
        startActivity(callIntent);

    }
}
