package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginscreen.R;

public class MeetActivity extends AppCompatActivity {

    ImageButton buttonZoom, buttonWebex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);

        buttonZoom = (ImageButton) findViewById(R.id.buttonZoom);
        buttonZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openZoom();
            }
        });

        buttonWebex = (ImageButton) findViewById(R.id.buttonWebex);
        buttonWebex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebex();
            }
        });


    }

    public void openZoom() {

        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("us.zoom.videomeetings");
        if (intent != null) {
            startActivity(intent);

        }
    }

    public void openWebex() {

        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.cisco.webex.meetings");
        if (intent != null) {
            startActivity(intent);

        }
    }

}
