package com.example.loginscreen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.web.client.RestTemplate;
import android.os.Bundle;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.StrictMode;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.loginscreen.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageButton chatButton, eventsButton, uploadButton, meetButton, contactsButton;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

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

    public void openChat(){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void openUpload(){
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    public void openEvents(){
        Intent intent = new Intent(this, EventsMainActivity.class);
        startActivity(intent);
    }

    public void openMeet(){
        Intent intent = new Intent(this, MeetActivity.class);
        startActivity(intent);
    }

    public void openContacts(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
    public void logout(){
        UserActivity.loginUsername = null;
        UserActivity.loginPassword = null;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

}