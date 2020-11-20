package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginscreen.R;

/**
 * The Meet page activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * This page allows the user to connect to Zoom or Webex depending on the users needs
 */
public class MeetActivity extends AppCompatActivity {

    ImageButton buttonZoom, buttonWebex;

    /**
     * creates the meeting page when the user navigates there from the main menu
     * @param savedInstanceState
     */
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

    /**
     * opens the zoom application when the user clicks the button for Zoom meetings
     */
    public void openZoom() {

        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("us.zoom.videomeetings");
        if (intent != null) {
            startActivity(intent);

        }
    }

    /**
     * opens the webex application when the user clicks the button for Webex meetings
     */
    public void openWebex() {

        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.cisco.webex.meetings");
        if (intent != null) {
            startActivity(intent);

        }
    }

}

//class mockitoTest{
//
//    //mock creation
//    List mockedList = mock(List.class);
//
//    //using mock object
// mockedList.add("one");
// mockedList.clear();
//
//    //verification
//    verify(mockedList).add("one");
//    verify(mockedList).clear();
//
//}

