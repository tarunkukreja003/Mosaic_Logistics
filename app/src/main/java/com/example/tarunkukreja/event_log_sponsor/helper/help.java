package com.example.tarunkukreja.event_log_sponsor.helper;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by deepak on 25/07/17.
 */

public class help {

    public static void shareTextUrl(Context context, String message, String subject) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        Intent new_intent = Intent.createChooser(shareIntent, "Share via");
        new_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(new_intent);
    }

    public static void openWebPage(Context context,String url) {

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }else {

        }
    }


    public static void instagram(Context context){
        Uri uri = Uri.parse("http://instagram.com/_u/xxx");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            context.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
           context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/xxxx")));
        }
    }
    public static void composeEmail(Context context, String sendTo, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + sendTo));
        // intent.putExtra(Intent.EXTRA_EMAIL, fromTo);
       // intent.putExtra(Intent.EXTRA_TEXT);
        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);


        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else
            Toast.makeText(context, "Gmail App is not installed", Toast.LENGTH_SHORT).show();
    }
}
