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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Test_Code";

    private TextView urlTextView, openedFromUrlTextView, dataTextView;

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
    }

    private void copyText(Context context, String textLabel, String textForCopy){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(textLabel, textForCopy);
        clipboardManager.setPrimaryClip(clipData);
    }
}