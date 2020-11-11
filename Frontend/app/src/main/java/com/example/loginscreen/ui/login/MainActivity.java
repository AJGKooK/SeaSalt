package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginscreen.R;

/**
 * The main page activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * This page is displayed once the user logins in successfully.
 * The page offers all the features that the app has and can bring you to every one of them
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton chatButton, eventsButton, uploadButton, meetButton, contactsButton;
    private Button logout;

    /**
     * When a user logs in successfully or returns from a different feature back to the main
     * menu, this creates the page for the user to be able to choose further navigation around the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        /**
         * Logs the user out of the application
         */
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        chatButton = (ImageButton) findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChat();
            }
        });
        uploadButton = (ImageButton) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpload();
            }
        });
        eventsButton = (ImageButton) findViewById(R.id.eventsButton);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEvents();
            }
        });
        meetButton = (ImageButton) findViewById(R.id.meetButton);
        meetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMeet();
            }
        });
        contactsButton = (ImageButton) findViewById(R.id.contactsButton);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContacts();
            }
        });


    }

    /**
     * opens the messenger interface
     */
    public void openChat(){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    /**
     * opens the upload interface
     */
    public void openUpload(){
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    /**
     * opens the events interface
     */
    public void openEvents(){
        Intent intent = new Intent(this, EventsMainActivity.class);
        startActivity(intent);
    }

    /**
     * opens the meetings interface
     */
    public void openMeet(){
        Intent intent = new Intent(this, MeetActivity.class);
        startActivity(intent);
    }

    /**
     * opens the contacts interface
     */
    public void openContacts(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    /**
     * logs the user out of the application
     * this function sets the login username and pass to null, but doesnt change the temp
     * username and password to null to prevent the user from still being able to access
     * application features while logged out. This then leads the user back to the login page.
     */
    public void logout(){
        UserActivity.loginUsername = null;
        UserActivity.loginPassword = null;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

}