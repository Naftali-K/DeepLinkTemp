package com.nk.deeplinktemp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * https://developer.android.com/training/app-links - about deep link
 * https://developer.android.com/training/app-links/verify-android-applinks#manual-verification - verification deep link
 *
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Test_Code";

    private TextView urlTextView, openedFromUrlTextView, dataTextView, userIdTextView, postIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if (data != null){
            Log.d(TAG, "onCreate: Open by URL link: \nAction: " + action + "\nData: " + data.toString());
            openedFromUrlTextView.setVisibility(View.VISIBLE);
            dataTextView.setText(data.toString());
            getInfoFromUri(data);
        }

        urlTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyText(getBaseContext(), "Url copied", urlTextView.getText().toString());
            }
        });
    }

    private void setReferences(){
        urlTextView = findViewById(R.id.url_text_view);
        openedFromUrlTextView = findViewById(R.id.opened_from_url_text_view);
        dataTextView = findViewById(R.id.data_text_view);

        userIdTextView = findViewById(R.id.user_id_text_view);
        postIdTextView = findViewById(R.id.post_id_text_view);
    }

    private void copyText(Context context, String textLabel, String textForCopy){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(textLabel, textForCopy);
        clipboardManager.setPrimaryClip(clipData);
    }

    private void getInfoFromUri(Uri uri) {
        String urlString = uri.toString();
//        Log.d(TAG, "getInfoFromUri: String URL: " + urlString);
        String[] urlSplit = urlString.split("/");
//        Log.d(TAG, "getInfoFromUri: List strings: " + urlSplit.toString() + "\nList size: " + urlSplit.length);

        String section = urlSplit[urlSplit.length-2];
        String id = urlSplit[urlSplit.length-1];
        Log.d(TAG, "getInfoFromUri: Open \tSection: " + section + "\tID: " + id);

        if (section.equals(SectionsEnum.POST.getSectionString())) {
            postIdTextView.setText(id);
        }
        if (section.equals(SectionsEnum.USER.getSectionString())) {
            userIdTextView.setText(id);
        }
    }
}